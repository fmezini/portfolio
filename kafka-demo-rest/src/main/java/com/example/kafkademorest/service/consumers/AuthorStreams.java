package com.example.kafkademorest.service.consumers;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorStreams {

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("author.created", Consumed.with(Serdes.String(), Serdes.String()));

        KTable<String, Long> wordCounts = messageStream
                .selectKey((key, value) -> key)
                .groupByKey()
                .count();

        wordCounts.toStream().mapValues(v -> v.toString()).to("author.count", Produced.with(Serdes.String(), Serdes.String()));
    }
}
