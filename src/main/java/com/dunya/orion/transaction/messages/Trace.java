package com.dunya.orion.transaction.messages;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trace {
	private Long elapsed;

	private String id;

	private ActionTrace[] actionTraces;

	private Boolean scheduled;

	private Object failedDtrxTrace;

	private String blockTime;

	private Long blockNum;

	private Long netUsage;

	private TraceReceipt receipt;

	public Long getElapsed() {
		return elapsed;
	}

	public void setElapsed(Long elapsed) {
		this.elapsed = elapsed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ActionTrace[] getActionTraces() {
		return actionTraces;
	}

	@JsonProperty("action_traces")
	public void setActionTraces(ActionTrace[] actionTraces) {
		this.actionTraces = actionTraces;
	}

	public Boolean getScheduled() {
		return scheduled;
	}

	public void setScheduled(Boolean scheduled) {
		this.scheduled = scheduled;
	}

	public Object getFailedDtrxTrace() {
		return failedDtrxTrace;
	}

	@JsonProperty("failed_dtrx_trace")
	public void setFailedDtrxTrace(Object failedDtrxTrace) {
		this.failedDtrxTrace = failedDtrxTrace;
	}

	public String getBlockTime() {
		return blockTime;
	}

	@JsonProperty("block_time")
	public void setBlock_time(String blockTime) {
		this.blockTime = blockTime;
	}

	public Long getBlockNum() {
		return blockNum;
	}

	@JsonProperty("block_num")
	public void setBlockNum(Long blockNum) {
		this.blockNum = blockNum;
	}

	public Long getNetUsage() {
		return netUsage;
	}

	@JsonProperty("net_usage")
	public void setNetUsage(Long netUsage) {
		this.netUsage = netUsage;
	}

	public TraceReceipt getReceipt() {
		return receipt;
	}

	public void setReceipt(TraceReceipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trace [elapsed=").append(elapsed).append(", id=").append(id).append(", actionTraces=")
				.append(Arrays.toString(actionTraces)).append(", scheduled=").append(scheduled)
				.append(", failedDtrxTrace=").append(failedDtrxTrace).append(", blockTime=").append(blockTime)
				.append(", blockNum=").append(blockNum).append(", netUsage=").append(netUsage).append(", receipt=")
				.append(receipt).append("]");
		return builder.toString();
	}
}