package com.dunya.orion.transaction.messages;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionTrace {

    private Act[] act;

    private String console;

    private Integer cpuUsage;

    private Integer elapsed;

    //private List<Object> inlineTraces;

    private ActionTraceReceipt receipt;

    private Integer totalCpuUsage;

    private String trxId;

    private boolean contextFree;
    
    private long blockNum;

    private String blockTime;

    private String producerBlockId;

    //private long[] accountRamDeltas;

    private String trxStatus;

    private String createdAt;

    public Act[] getAct() {
        return act;
    }

    @JsonProperty("act")
    public void setAct(Act[] act) {
        this.act = act;
    }

    public String getConsole() {
        return console;
    }

    @JsonProperty("console")
    public void setConsole(String console) {
        this.console = console;
    }

    public Integer getCpuUsage() {
        return cpuUsage;
    }

    @JsonProperty("cpu_usage")
    public void setCpuUsage(Integer cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    /*public List<Object> getInlineTraces() {
        return inlineTraces;
    }

    @JsonProperty("inline_traces")
    public void setInlineTraces(List<Object> inlineTraces) {
        this.inlineTraces = inlineTraces;
    }*/

    public ActionTraceReceipt getReceipt() {
        return receipt;
    }

    @JsonProperty("receipt")
    public void setReceipt(ActionTraceReceipt receipt) {
        this.receipt = receipt;
    }

    public Integer getTotalCpuUsage() {
        return totalCpuUsage;
    }

    @JsonProperty("total_cpu_usage")
    public void setTotalCpuUsage(Integer totalCpuUsage) {
        this.totalCpuUsage = totalCpuUsage;
    }

    public String getTrxId() {
        return trxId;
    }

    @JsonProperty("trx_id")
    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public boolean isContextFree() {
        return contextFree;
    }

    @JsonProperty("context_free")
    public void setContextFree(boolean contextFree) {
        this.contextFree = contextFree;
    }

    public long getBlockNum() {
        return blockNum;
    }

    @JsonProperty("block_num")
    public void setBlockNum(long blockNum) {
        this.blockNum = blockNum;
    }

    public String getBlockTime() {
        return blockTime;
    }
    @JsonProperty("block_time")
    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

    public String getProducerBlockId() {
        return producerBlockId;
    }

    @JsonProperty("producer_block_id")
    public void setProducerBlockId(String producerBlockId) {
        this.producerBlockId = producerBlockId;
    }

    /*public long[] getAccountRamDeltas() {
        return accountRamDeltas;
    }
    @JsonProperty("account_ram_deltas")
    public void setAccountRamDeltas(long[] accountRamDeltas) {
        this.accountRamDeltas = accountRamDeltas;
    }*/

    public String getTrxStatus() {
        return trxStatus;
    }
    @JsonProperty("trx_status")
    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActionTrace [act=").append(Arrays.toString(act)).append(", console=").append(console)
				.append(", cpuUsage=").append(cpuUsage).append(", elapsed=").append(elapsed).append(", receipt=")
				.append(receipt).append(", totalCpuUsage=").append(totalCpuUsage).append(", trxId=").append(trxId)
				.append(", contextFree=").append(contextFree).append(", blockNum=").append(blockNum)
				.append(", blockTime=").append(blockTime).append(", producerBlockId=").append(producerBlockId)
				.append(", trxStatus=").append(trxStatus).append(", createdAt=").append(createdAt).append("]");
		return builder.toString();
	}
    
}
