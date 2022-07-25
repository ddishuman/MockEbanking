package com.ebanking.payment.resource;

import com.ebanking.payment.model.MyUser;
import com.ebanking.payment.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    private KafkaTemplate<String, MyUser> kafkaTemplate;

    private static final String TOPIC ="Kafka_Example";

    private final MyUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/kafka/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        // ### TODO: kafka msg format
        //kafkaTemplate.send(TOPIC, new MyUser(name, "IT", 12000L));

        return "Published successfully!";
    }
}
