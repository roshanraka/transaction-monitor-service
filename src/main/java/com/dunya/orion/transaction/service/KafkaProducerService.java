package com.dunya.orion.transaction.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.dunya.orion.transaction.messages.TxnActions;

@EnableKafka
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.producer.topic.name}")
    private String topicName;

    private Logger log = LogManager.getLogger(this.getClass().getName());

    public void sendTxnActions(TxnActions txnActions) {
        // log.info("Send action: {}", txnActions);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, txnActions.toJson());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
//                log.info("Sent Action=[{}] at offset=[{}]", result.getProducerRecord().value(),
//                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send action due to : {}", ex.getMessage());
            }
        });
    }
}
