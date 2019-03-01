package com.dunya.orion.transaction.messages;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Act {

    private String account;

    private List<Authorization> authorization;

    private String data;

    private String name;

    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
    }

    public List<Authorization> getAuthorization() {
        return authorization;
    }

    @JsonProperty("authorization")
    public void setAuthorization(List<Authorization> authorization) {
        this.authorization = authorization;
    }

    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean isAuthorizationValid() {
    	if (this.getAuthorization() != null && !this.getAuthorization().isEmpty()
				&& this.getAuthorization().get(0) != null)
    		return true;
    	else 
    		return false;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Act [account=").append(account).append(", authorization=").append(authorization)
				.append(", data=").append(data).append(", name=").append(name).append("]");
		return builder.toString();
	}
}