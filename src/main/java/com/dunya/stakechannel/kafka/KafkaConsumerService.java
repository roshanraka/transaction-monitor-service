package com.dunya.stakechannel.kafka;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.dunya.stakechannel.model.AccountAction;
import com.dunya.stakechannel.model.Act;
import com.dunya.stakechannel.model.Transaction;
import com.dunya.stakechannel.repositories.AccountActionRepository;
import com.dunya.stakechannel.utils.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
	CountDownLatch cdl1 = new CountDownLatch(2);
	CountDownLatch cdl2 = new CountDownLatch(2);
	
	@Autowired
    ObjectMapper objectMapper;
	
	@Autowired
	AccountActionRepository accountActionRepository;
	
	@KafkaListener(topicPartitions = { @TopicPartition(topic = "transactions", partitions = { "0" }) })
	public void consumeMessageFromPartition0(String message) {
		Utils.logger.info("Thread ID: " + Thread.currentThread().getId() + ", Received Message: " + message+"\n");
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Transaction transaction;
		try {
			transaction = objectMapper.readValue(message, Transaction.class);
			this.makeAccountAction(transaction);
		} catch (IOException e) {
			Utils.logger.error(e.getMessage());
		}
		cdl1.countDown();
	}

	@KafkaListener(topicPartitions = { @TopicPartition(topic = "transactions", partitions = { "1" }) })
	public void consumeMessageFromPartition1(String message) {
		Utils.logger.info("Thread ID: " + Thread.currentThread().getId() + ", Received Message: " + message+"\n");
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Transaction transaction;
		try {
			transaction = objectMapper.readValue(message, Transaction.class);
			this.makeAccountAction(transaction);
		} catch (IOException e) {
			Utils.logger.error(e.getMessage());
		}
		cdl2.countDown();
	}
	
	public synchronized void makeAccountAction(Transaction transaction) {
		HashMap<AbstractMap.SimpleImmutableEntry<String, String>, Integer> map = new HashMap<>();
		for(Act act: transaction.getTrace().getAction_traces()[0].getAct()) {
			AbstractMap.SimpleImmutableEntry<String, String> entry
			  = new AbstractMap.SimpleImmutableEntry<>(act.getAccount(), act.getName());
			map.merge(entry, 1, Integer::sum);
		}
		
		List<AccountAction> entites = new ArrayList<>();
		Iterator<Map.Entry<AbstractMap.SimpleImmutableEntry<String, String>, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<AbstractMap.SimpleImmutableEntry<String, String>, Integer> pair = it.next();
		    AccountAction accountAction = new AccountAction();
			accountAction.setTxId(transaction.getTrace().getId());
			accountAction.setBlockTime(transaction.getBlockTime());
		    accountAction.setAccountName(pair.getKey().getKey());
		    accountAction.setAction(pair.getKey().getValue());
		    accountAction.setCount(pair.getValue());
		    entites.add(accountAction);
		    Utils.logger.info(accountAction.toString());
		}
		accountActionRepository.saveAll(entites);
	}
}
