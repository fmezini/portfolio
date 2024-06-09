package com.example.kafkademorest.service;

import com.example.kafkademorest.model.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper mapper;

    public AuthorDTO addAuthor(AuthorDTO author) {
        try {
            log.info("Producing {}", author);
            // and now we are notifying other systems using kafka
            kafkaTemplate.send("author.created", author.getId().toString(), mapper.writeValueAsString(author));
            return author;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
