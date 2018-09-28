package com.dunya.stakechannel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trace {
	private String elapsed;

	private String id;

	private ActionTrace[] actionTraces;

	private String scheduled;

	private Object failedDtrxTrace;

	private String blockTime;

	private String blockNum;

	private String netUsage;

	private TraceReceipt receipt;

	public String getElapsed() {
		return elapsed;
	}

	public void setElapsed(String elapsed) {
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

	public String getScheduled() {
		return scheduled;
	}

	public void setScheduled(String scheduled) {
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

	public String getBlockNum() {
		return blockNum;
	}

	@JsonProperty("block_num")
	public void setBlockNum(String blockNum) {
		this.blockNum = blockNum;
	}

	public String getNetUsage() {
		return netUsage;
	}

	@JsonProperty("net_usage")
	public void setNetUsage(String netUsage) {
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
		return "ClassPojo [elapsed = " + elapsed + ", id = " + id + ", action_traces = " + actionTraces
				+ ", scheduled = " + scheduled + ", failed_dtrx_trace = " + failedDtrxTrace + ", block_time = "
				+ blockTime + ", block_num = " + blockNum + ", net_usage = " + netUsage + ", receipt = " + receipt
				+ "]";
	}
}