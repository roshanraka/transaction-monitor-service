package com.dunya.orion.transaction.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dunya.orion.transaction.contract.AccountName;
import com.dunya.orion.transaction.contract.Blacklist;
import com.dunya.orion.transaction.contract.ErrorResponse;
import com.dunya.orion.transaction.contract.ResourceUsage;
import com.dunya.orion.transaction.dao.AccountActionsRepositoryImpl;
import com.dunya.orion.transaction.messages.Act;
import com.dunya.orion.transaction.messages.Action;
import com.dunya.orion.transaction.messages.ActionTrace;
import com.dunya.orion.transaction.messages.Transaction;
import com.dunya.orion.transaction.messages.TxnActions;
import com.dunya.orion.transaction.model.AccountAction;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionProcessService {

	private Logger log = LogManager.getLogger(this.getClass().getName());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Autowired
	private AccountActionsRepositoryImpl accountActionsRepositoryImpl;

	private int noOfTrials = 1;

	@Value("#{'${transaction.filter.actions:}'.split(',')}")
	private Set<String> actionFilter;

	@Value("#{'${transaction.filter.accounts:}'.split(',')}")
	private Set<String> accountFilter;

	@Value("#{'${transaction.accept.accounts:}'.split(',')}")
	private Set<String> accountAccept;

	@Value("#{'${transaction.blacklist.accounts:}'.split(',')}")
	private Set<String> blacklistDapp;

	@Value("${enable.billing}")
	private boolean enableBilling;

	@Value("${enable.delegation}")
	private boolean enableDelegation;

	@Value("${enable.transaction.filter}")
	private boolean enableTransactionFilter;

	@Value("${enable.transaction.accept}")
	private boolean enableTransactionAccept;

	@Value("${accountService.uri}")
	private String accountServiceUri;

	@Value("${delegationService.uri}")
	private String delegationServiceUri;

	@Value("${billingService.uri}")
	private String billingServiceUri;

	@Value("${accountService.blacklistAccountPath}")
	private String blacklistAccountPath;

	@Value("${delegationService.delegatePath}")
	private String delegatePath;

	@Value("${billingService.generateBillPath}")
	private String generateBillPath;

	@PostConstruct
	public void postConstruct() {
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
		log.info("TransactionFilter enabled: {}", enableTransactionFilter);
		log.info("TransactionAccept enabled: {}", enableTransactionAccept);
		log.info("Delegation enabled: {}", enableDelegation);
		log.info("Billing enabled: {}", enableBilling);
		log.info("Action filters: {}", actionFilter.toString());
		log.info("Account filters: {}", accountFilter.toString());
		log.info("Account acceptors: {}", accountAccept.toString());
		log.info("Blacklist Account(dapp): {}", blacklistDapp.toString());
	}

	public void process(String key, String message) {
		// Transaction transaction;
		try {
			Transaction transaction = objectMapper.readValue(message, Transaction.class);
			// log.info("Transaction: " + transaction.toString());
			Set<String> actors = new LinkedHashSet<>();
			TxnActions txnActions = new TxnActions();
			List<AccountAction> accountActions = new ArrayList<>();
			List<Pair<String, ResourceUsage>> resourceUsageList = new ArrayList<>();
			txnActions.setBlockNumber(transaction.getBlockNumber());
			txnActions.setBlockTime(transaction.getBlockTime());
			txnActions.setCpuUsage(transaction.getTrace().getReceipt().getCpuUsageUs());
			txnActions.setNetUsage(transaction.getTrace().getNetUsage().intValue());
			List<Action> actionList = new ArrayList<>();
			int index = 0;
			for (ActionTrace actionTrace : transaction.getTrace().getActionTraces()) {
				for (Act act : actionTrace.getAct()) {
					if (act.isAuthorizationValid()) {
						String actor = act.getAuthorization().get(0).getActor();
						// filters mostly for system accounts
						boolean filterAccount = enableTransactionFilter && !accountFilter.contains(actor);
						// list of actors we delegate for
						boolean acceptAccount = enableTransactionAccept && accountAccept.contains(actor);
						if (!actionFilter.contains(act.getName()) && (filterAccount || acceptAccount)) {
							// blacklist actor if account(contact code) is in the list
							if (blacklistDapp.contains(act.getAccount())) {
								this.blacklistAccount(actor);
							}
							AccountAction accountAction = AccountAction.newFromTransaction(transaction, act, actor,
									index);
							accountActions.add(accountAction);
							actors.add(actor); // set of unique actors for DS to be called
							// Used by POC billing service
							if (enableBilling) {
								ResourceUsage resourceUsage = ResourceUsage.newFromTransaction(transaction, act);
								Pair<String, ResourceUsage> resourcePair = Pair.of(accountAction.getAccountName(),
										resourceUsage);
								resourceUsageList.add(resourcePair);
							}
							Action action = new Action(act);
							action.setData(accountAction.getData());
							actionList.add(action);
						}
					}
				}
				index++;
			}
			txnActions.setActions(actionList);
			txnActions.setTxId(transaction.getTrace().getId());
			// save to MongoDB (Account Service)
			if (!accountActions.isEmpty()) {
				long beforeAccount = Instant.now().toEpochMilli();
				List<AccountAction> accountActionsList = accountActionsRepositoryImpl
						.saveAccountActions(accountActions);
				long afterAccount = Instant.now().toEpochMilli();
				long afterBilling = 0;
				long afterDelegation = 0;

				if (accountActionsList != null && !accountActionsList.isEmpty()) {
					kafkaProducerService.sendTxnActions(txnActions);
					if (enableDelegation) {
						CompletableFuture.runAsync(() -> callToDelegationService(actors));
					}
					afterDelegation = Instant.now().toEpochMilli();
					if (enableBilling) {
						CompletableFuture.supplyAsync(() -> callToBillingService(resourceUsageList));
					}
					afterBilling = Instant.now().toEpochMilli();
				}
				log.info(
						"Finished Processing transaction data, action count: {}, for key: {}, duration for calls account service: {}, delegation service: {}, billing service: {}, ",
						accountActions.size(), key, afterAccount - beforeAccount, afterDelegation - afterAccount,
						afterBilling - afterDelegation);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to process message: " + e);
		}
	}

	public void blacklistAccount(String account) {
		log.info("Invoking Blaclist API for account : {}", account);
		Blacklist blacklist = new Blacklist();
		blacklist.setBlacklist(1);
		try {
			this.restTemplate.put(accountServiceUri + blacklistAccountPath, blacklist, account);
		} catch (Exception e) {
			log.error("Failure in calling Blacklist API for account: {}", account);
		}
	}

	public ResponseEntity<ErrorResponse> callToDelegationService(Set<String> accountNames) {
		log.info("Invoking Delegation Service. #accounts : {}", accountNames.size());
		for (String accountName : accountNames) {
			AccountName account = new AccountName();
			account.setAccountName(accountName);
			this.makePostCalls(delegationServiceUri + delegatePath, account);
		}
		return ResponseEntity.ok().body(ErrorResponse.newSuccessResponse());
	}

	public ResponseEntity<ErrorResponse> callToBillingService(List<Pair<String, ResourceUsage>> resourceUsageList) {
		log.info("Invoking Billing Service. #accounts : {}", resourceUsageList.size());
		resourceUsageList.forEach(pair -> {
			ResourceUsage resourceUsage = pair.getSecond();
			String accountName = pair.getFirst();
			String url = billingServiceUri + generateBillPath;
			this.makePostCalls(url, resourceUsage, accountName);
		});
		return ResponseEntity.ok().body(ErrorResponse.newSuccessResponse());
	}

	public ResponseEntity<ErrorResponse> makePostCalls(String url, Object request, Object... uriVariables) {

		int i = 0;
		while (i < noOfTrials) {
			i++;
			try {
				log.info("POST " + url + " Request Body:" + request.toString());
				ResponseEntity<ErrorResponse> re = restTemplate.postForEntity(url, request, ErrorResponse.class,
						uriVariables);
				if (re.getStatusCode().equals(HttpStatus.OK)) {
					return re;
				} else {
					log.info("POST call Failure: " + url + " Request Body:" + request.toString());
				}
			} catch (RestClientException e) {
				log.warn("POST call Failure: " + url + " Request Body:" + request.toString() + " Error Message: "
						+ e.getMessage());
			} catch (Exception e) {
				log.warn("POST call Failure: " + url + " Request Body:" + request.toString() + " Error Message: "
						+ e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorResponse.newErrorResponse(500, "POST call to other " + "service, sattus: failed"));
	}
}
