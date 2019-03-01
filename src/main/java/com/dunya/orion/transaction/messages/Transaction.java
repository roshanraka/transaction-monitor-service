package com.dunya.orion.transaction.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	@JsonCreator
	public Transaction(@JsonProperty("trace") Trace trace, @JsonProperty("block_time") long blockTime,
			@JsonProperty("block_number") long blockNumber, @JsonProperty("decoded_data") DecodedData decodedData) {
		this.trace = trace;
		this.blockTime = blockTime;
		this.blockNumber = blockNumber;
		this.decodedData = decodedData;
	}

	private final Trace trace;

	@JsonProperty("block_time")
	private long blockTime;

	@JsonProperty("block_number")
	private long blockNumber;

	@JsonProperty("decoded_data")
	private DecodedData decodedData;

	public Trace getTrace() {
		return trace;
	}

	/*
	 * public void setTrace(Trace trace) { this.trace = trace; }
	 */

	public long getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(long blockTime) {
		this.blockTime = blockTime;
	}

	public long getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public DecodedData getDecodedData() {
		return decodedData;
	}

	public void setDecodedData(DecodedData decodedData) {
		this.decodedData = decodedData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [trace=").append(trace).append(", blockTime=").append(blockTime)
				.append(", blockNumber=").append(blockNumber).append(", decodedData=").append(decodedData.toString()).append("]");
		return builder.toString();
	}
}
