package com.dunya.orion.transaction.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private Logger log = LogManager.getLogger(this.getClass().getName());

    @Autowired
    TransactionProcessService transactionProcessService;


    @KafkaListener(topics = "${kafka.consumer.topic.name}")
    public void listen(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment) {
        String message = consumerRecord.value().toString();
        String key = Thread.currentThread().getId() + new StringBuilder().append(".").append
                (consumerRecord.partition()).append(".").append(consumerRecord.offset()).toString();
        log.debug(new StringBuilder("\n\nRecord key: ").append(key).append(", Received Message: ")
                .append(message));
        acknowledgment.acknowledge();
        transactionProcessService.process(key, message);
    }
}
