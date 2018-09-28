package com.dunya.stakechannel.kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
			this.makeAccountActionPair(transaction);
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
			this.makeAccountActionPair(transaction);
		} catch (IOException e) {
			Utils.logger.error(e.getMessage());
		}
		cdl2.countDown();
	}
	
	public synchronized void makeAccountActionPair(Transaction transaction) {
		HashMap<Pair<String, String>, Integer> map = new HashMap<>();
		
		// counts unique account-action
		for(Act act: transaction.getTrace().getActionTraces()[0].getAct()) {
			Pair<String, String> pair = Pair.of(act.getAccount(), act.getName());
			map.merge(pair, 1, Integer::sum);
		}
		
		List<AccountAction> entites = new ArrayList<>();
		Iterator<Map.Entry<Pair<String, String>, Integer>> it = map.entrySet().iterator();
		
		while (it.hasNext()) {
		    Map.Entry<Pair<String, String>, Integer> entity = it.next();
		    AccountAction accountAction = new AccountAction();
			accountAction.setTxId(transaction.getTrace().getId());
			accountAction.setBlockTime(transaction.getBlockTime());
		    accountAction.setAccountName(entity.getKey().getFirst());
		    accountAction.setAction(entity.getKey().getSecond());
		    accountAction.setCount(entity.getValue());
		    
		    entites.add(accountAction);
		    Utils.logger.info(accountAction.toString());
		}
		// save to MongoDB
		accountActionRepository.saveAll(entites);
	}
}
