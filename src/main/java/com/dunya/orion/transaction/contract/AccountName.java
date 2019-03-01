package com.dunya.orion.transaction.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountName {
	
	@JsonProperty("account")
	public String accountName;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "Account [accountName=" + accountName + "]";
	}
}
