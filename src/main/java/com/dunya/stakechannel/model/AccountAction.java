package com.dunya.stakechannel.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="account_action")
public class AccountAction {
	@Id
	public ObjectId _id;
	
	@Indexed
	@Field("account_name")
	private String accountName;
	
	private String action;
	
	@Field("block_time")
	private long blockTime;
	
	private Integer count;
	
	@Field("tx_id")
	private String txId;
	
	
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

	public long getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(long blockTime) {
		this.blockTime = blockTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	@Override
	public String toString() {
		return "AccountAction [accountName=" + accountName + ", action=" + action + ", blockTime=" + blockTime
				+ ", count=" + count + ", txId=" + txId + "]";
	}
}
