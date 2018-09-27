package com.dunya.stakechannel.model;

public class AccountAction {
	private String accountName;
	private String action;
	private long blockTime;
	private Integer count;
	private String txId;
	
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
