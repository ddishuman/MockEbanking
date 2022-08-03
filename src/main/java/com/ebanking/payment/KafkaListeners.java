package com.ebanking.payment;

import com.ebanking.payment.model.Transaction;
import com.ebanking.payment.repository.TransactionRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    private final TransactionRepo transactionRepo;

    public KafkaListeners(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    @KafkaListener(
            topics = "test",
            groupId = "groupId"
    )
    void consume(String data) throws JsonProcessingException {
        //log
        System.out.print("Txns received: " + data + ":)");
        Transaction transaction = new ObjectMapper().readValue(data, Transaction.class);
        transactionRepo.save(transaction);
    }
}
