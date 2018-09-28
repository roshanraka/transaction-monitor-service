package com.dunya.stakechannel.config;

public interface Constants {
	public static String KAFKA_BROKERS = "localhost:9092";
	public static Integer MESSAGE_COUNT = 1000;
	public static String CLIENT_ID = "client1";
	public static String TOPIC_ACCOUNTS = "accounts";
	public static String TOPIC_ACTIONS = "actions";
	public static String GROUP_ID_CONFIG = "transactionGroup";
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;
	public static String OFFSET_RESET_LATEST = "latest";
	public static String OFFSET_RESET_EARLIER = "earliest";
	public static Integer MAX_POLL_RECORDS = 1;
	
	// EOS js
	
	public static String EOS_JS_HOST_URL = "localhost:8080";
}
