package com.example.kafkademorest.service.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorConsumer {
    @KafkaListener(topics = "author.created")
    public void processAuthorCreated(String content) {
        log.info("Consuming {}", content);
    }
}
