package com.dunya.orion.transaction.contract;

import java.time.Instant;

import com.dunya.orion.transaction.messages.Act;
import com.dunya.orion.transaction.messages.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceUsage {

	private String action;
	@JsonProperty("block_time")
	private long blockTime;
	private Resources resources;
	private long timestamp;
	@JsonProperty("tx_id")
	private  String txId;
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
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResourceUsage [action=").append(action).append(", blockTime=").append(blockTime)
				.append(", resources=").append(resources).append(", timestamp=").append(timestamp).append(", txId=")
				.append(txId).append("]");
		return builder.toString();
	}
	
	public static ResourceUsage newFromTransaction(Transaction transaction, Act act) {
		ResourceUsage resourceUsage = new ResourceUsage();
		resourceUsage.setTxId(transaction.getTrace().getId());
		resourceUsage.setBlockTime(transaction.getBlockTime());
		resourceUsage.setTimestamp(Instant.now().toEpochMilli());
		resourceUsage.setAction(act.getName());
		Resources resources = new Resources();
		resources.setCpuUsage(transaction.getTrace().getReceipt().getCpuUsageUs());
		resources.setNetUsage(transaction.getTrace().getReceipt().getNetUsageWords());
		resourceUsage.setResources(resources);
		return resourceUsage;
	}
}
