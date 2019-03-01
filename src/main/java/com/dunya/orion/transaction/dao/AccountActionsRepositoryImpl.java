package com.dunya.orion.transaction.dao;

import java.time.Instant;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.dunya.orion.transaction.model.Account;
import com.dunya.orion.transaction.model.AccountAction;

public class AccountActionsRepositoryImpl implements AccountActionsRepositoryCustom {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	AccountActionsRepository accountActionsRepository;
	
	@Autowired
	AccountsRepository accountsRepository;
			
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public List<AccountAction> saveAccountActions(List<AccountAction> actionsList) {
		actionsList.stream().forEach(action -> {
			try {
				Query query = new Query();
				query.addCriteria(Criteria.where("account_name").is(action.getAccountName()));
				Update update = new Update();
				update.set("last_updated_time", Instant.now().toEpochMilli());
				update.set("last_action_time", action.getBlockTime());
				Account account = mongoOperations.findAndModify(query, update, Account.class);
				if (account == null) {
					account = new Account();
					account.setAccountName(action.getAccountName());
					account.setTimestamp(Instant.now().toEpochMilli());
					account.setLastActionTime(action.getBlockTime());
					account.setLastUpdatedTime(Instant.now().toEpochMilli());
					accountsRepository.save(account);
				}
			} catch (Exception e) {
				log.error("Error while saving action: {} with exception: {}", action, e);
			}
		});
		return accountActionsRepository.saveAll(actionsList);
	}
}
