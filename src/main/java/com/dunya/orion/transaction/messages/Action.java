package com.dunya.orion.transaction.messages;

import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Action {

    private String account;

    private List<Authorization> authorization;

    private JSONObject data;

    private String name;
    

    public Action(Act act) {
		super();
		this.account = act.getAccount();
		this.authorization = act.getAuthorization();
		this.name = act.getName();
	}

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

    public JSONObject getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Act [account=").append(account).append(", authorization=").append(authorization)
				.append(", data=").append(data).append(", name=").append(name).append("]");
		return builder.toString();
	}
}