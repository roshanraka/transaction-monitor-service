package com.dunya.orion.transaction.messages;

import java.util.List;

import com.dunya.orion.transaction.uitl.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TxnActions {

    private Long blockNumber;
    private Long blockTime;
    private String txId;
    private List<Action> actions;
    private Integer cpuUsage;
    private Integer netUsage;

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

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Integer getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Integer cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Integer getNetUsage() {
        return netUsage;
    }

    public void setNetUsage(Integer netUsage) {
        this.netUsage = netUsage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TxnActions [blockNumber=").append(blockNumber).append(", blockTime=").append(blockTime)
                .append(", txId=").append(txId).append(", actions=").append(actions).append(", cpuUsage=")
                .append(cpuUsage).append(", netUsage=").append(netUsage).append("]");
        return builder.toString();
    }

    public String toJson() {
        try {
            return ObjectMapperFactory.newObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing class: " + this.getClass(), e);
        }
    }
}
