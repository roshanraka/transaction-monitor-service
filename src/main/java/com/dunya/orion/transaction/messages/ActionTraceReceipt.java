package com.dunya.orion.transaction.messages;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionTraceReceipt {

    private long abiSequence;

    private String actDigest;

    private List<List<String>> authSequence;

    private long codeSequence;

    private long globalSequence;

    private String receiver;

    private long recvSequence;

    public long getAbiSequence() {
        return abiSequence;
    }

    @JsonProperty("abi_sequence")
    public void setAbiSequence(long abiSequence) {
        this.abiSequence = abiSequence;
    }

    public String getActDigest() {
        return actDigest;
    }

    @JsonProperty("act_digest")
    public void setActDigest(String actDigest) {
        this.actDigest = actDigest;
    }

    public List<List<String>> getAuthSequence() {
        return authSequence;
    }

    @JsonProperty("auth_sequence")
    public void setAuthSequence(List<List<String>> authSequence) {
        this.authSequence = authSequence;
    }

    public long getCodeSequence() {
        return codeSequence;
    }

    @JsonProperty("code_sequence")
    public void setCodeSequence(long codeSequence) {
        this.codeSequence = codeSequence;
    }

    public long getGlobalSequence() {
        return globalSequence;
    }

    @JsonProperty("global_sequence")
    public void setGlobalSequence(long globalSequence) {
        this.globalSequence = globalSequence;
    }

    public String getReceiver() {
        return receiver;
    }

    @JsonProperty("receiver")
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public long getRecvSequence() {
        return recvSequence;
    }

    @JsonProperty("recv_sequence")
    public void setRecvSequence(long recvSequence) {
        this.recvSequence = recvSequence;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActionTraceReceipt [abiSequence=").append(abiSequence).append(", actDigest=").append(actDigest)
				.append(", authSequence=").append(authSequence).append(", codeSequence=").append(codeSequence)
				.append(", globalSequence=").append(globalSequence).append(", receiver=").append(receiver)
				.append(", recvSequence=").append(recvSequence).append("]");
		return builder.toString();
	}

}