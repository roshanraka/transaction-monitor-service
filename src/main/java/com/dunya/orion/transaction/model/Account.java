package com.dunya.orion.transaction.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "accounts")
public class Account {
	@JsonProperty("tx_id")
	@Field("tx_id")
	private String txId;
	
	@JsonProperty("creator_account")
	@Field("creator_account")
	private String creatorAccount;
	
	@Indexed(unique = true)
	@JsonProperty("account_name")
	@Field("account_name")
	private String accountName;

	@JsonProperty("block_time")
	@Field("block_time")
	private long blockTime;

	@JsonProperty("block_num")
	@Field("block_num")
	private long blockNum;
	
	private long timestamp;

	private Float score;

	private int blacklist;
	
	@JsonProperty("blacklist_comment")
	@Field("blacklist_comment")
	private String blacklistComment;

	@JsonProperty("last_updated_time")
	@Field("last_updated_time")
	private long lastUpdatedTime;
	
	@JsonProperty("last_action_time")
	@Field("last_action_time")
	private long lastActionTime;

	public int getTracked() {
		return tracked;
	}

	public void setTracked(int tracked) {
		this.tracked = tracked;
	}

	@JsonProperty("tracked")
	@Field("tracked")
	private int tracked;

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getCreatorAccount() {
		return creatorAccount;
	}

	public void setCreatorAccount(String creatorAccount) {
		this.creatorAccount = creatorAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public long getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(long blockTime) {
		this.blockTime = blockTime;
	}

	public long getBlockNum() {
		return blockNum;
	}

	public void setBlockNum(long blockNum) {
		this.blockNum = blockNum;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public int getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}

	public long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBlacklistComment() {
		return blacklistComment;
	}

	public void setBlacklistComment(String blacklistComment) {
		this.blacklistComment = blacklistComment;
	}

	public long getLastActionTime() {
		return lastActionTime;
	}

	public void setLastActionTime(long lastActionTime) {
		this.lastActionTime = lastActionTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [txId=").append(txId).append(", creatorAccount=").append(creatorAccount)
				.append(", accountName=").append(accountName).append(", blockTime=").append(blockTime)
				.append(", blockNum=").append(blockNum).append(", timestamp=").append(timestamp).append(", score=")
				.append(score).append(", blacklist=").append(blacklist).append(", blacklistComment=")
				.append(blacklistComment).append(", lastUpdatedTime=").append(lastUpdatedTime)
				.append(", lastActionTime=").append(lastActionTime).append(", tracked=").append(tracked).append("]");
		return builder.toString();
	}
}
