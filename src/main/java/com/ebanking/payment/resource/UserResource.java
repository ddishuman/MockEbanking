package com.ebanking.payment.resource;

import com.ebanking.payment.model.MyUser;
import com.ebanking.payment.model.Transaction;
import com.ebanking.payment.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    //private static final String TOPIC ="Kafka_Example";
    //private static final String TOPIC ="Kafka_TXNs";
    private static final String TOPIC ="test";

    private final MyUserService userService;

    static Random rndCurrency = new Random();

    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/kafka/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        // ### kafka msg format
        for (int i=0; i< 1_000; i++) {
            kafkaTemplate.send(TOPIC, new Transaction(UUID.randomUUID(),
                    Currency.getCurrency().toString(),
                    rndCurrency.nextDouble(100) +1,
                    "GB54-BOFA-165050-12345678",
                    new Date(), "Online payment"));
        }

        return "Published successfully!";
    }

    private enum Currency {
            GBP,
            CHF,
            USD;

        private static Currency getCurrency() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}
