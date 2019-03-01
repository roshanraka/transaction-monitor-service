package com.dunya.orion.transaction.model;

import java.time.Instant;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.dunya.orion.transaction.messages.Act;
import com.dunya.orion.transaction.messages.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "account_actions")
public class AccountAction {

	private static Logger log = LogManager.getLogger("AccountAction");

	private String account;

	@Indexed
	@JsonProperty("account_name")
	@Field("account_name")
	private String accountName;

	@Field("action")
	private String action;

	@Indexed
	@JsonProperty("block_number")
	@Field("block_number")
	private Long blockNumber;

	@Indexed
	@JsonProperty("block_time")
	@Field("block_time")
	private Long blockTime;

	@JsonProperty("cpu_usage")
	@Field("cpu_usage")
	private Integer cpuUsage;
	
	@JsonProperty("dapp_account")
	@Field("dapp_account")
	private String dappAccount;

	private JSONObject data;

	@JsonProperty("net_usage")
	@Field("net_usage")
	private Integer netUsage;

	@JsonProperty("last_updated_time")
	@Field("last_updated_time")
	private Long lastUpdatedTime;

	@Indexed
	@JsonProperty("timestamp")
	@Field("timestamp")
	private Long timestamp;

	@JsonProperty("tx_id")
	@Field("tx_id")
	private String txId;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Long getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(Long blockTime) {
		this.blockTime = blockTime;
	}

	public Integer getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(Integer cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getDappAccount() {
		return dappAccount;
	}

	public void setDappAccount(String dappAccount) {
		this.dappAccount = dappAccount;
	}

	public Integer getNetUsage() {
		return netUsage;
	}

	public void setNetUsage(Integer netUsage) {
		this.netUsage = netUsage;
	}

	public Long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public static AccountAction newFromTransaction(Transaction transaction, Act act, String actor, Integer index) {
		AccountAction accountAction = new AccountAction();
		accountAction.setTxId(transaction.getTrace().getId());
		accountAction.setBlockNumber(transaction.getBlockNumber());
		accountAction.setBlockTime(transaction.getBlockTime());
		accountAction.setTimestamp(Instant.now().toEpochMilli());
		accountAction.setAccountName(actor);
		accountAction.setAction(act.getName());
		accountAction.setCpuUsage(transaction.getTrace().getReceipt().getCpuUsageUs());
		accountAction.setNetUsage(transaction.getTrace().getNetUsage().intValue());
		accountAction.setAccount(act.getAccount());
		String data = transaction.getDecodedData().getData(index);
		JSONObject jsonData = null;
		try {
			JSONParser parser = new JSONParser();
			if (data == null || data.length() == 0)
				jsonData = (JSONObject) parser.parse(act.getData());
			else
				jsonData = (JSONObject) parser.parse(data);
			accountAction.setData(jsonData);
		} catch (Exception e) {
			log.error("Error parsing jsonString data: {}, with excpetion: {}", data, e.getMessage());
		}
		
		if(jsonData != null && accountAction.action.equals("transfer"))
			accountAction.setDappAccount((String) jsonData.get("to"));
		else
			accountAction.setDappAccount(act.getAccount());
		
		return accountAction;
	}

}