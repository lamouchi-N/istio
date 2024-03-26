package com.soa.order.config;

import basedomains.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Properties;

@Slf4j
@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
                .build();
    }


    @Bean
    public KTable<Long, OrderDto> table(StreamsBuilder builder) {

        KeyValueBytesStoreSupplier store = Stores.persistentKeyValueStore(topicName);

        Serde<Long> keySerde =  Serdes.Long();
        JsonSerde<OrderDto> valueSerde = new JsonSerde<>(OrderDto.class);

        KStream<Long, OrderDto> stream = builder
                .stream(topicName, Consumed.with(keySerde, valueSerde))
                .peek((k,v)->log.info("Kafka persistence table: key[{}],value[{}]",k,v));

        return stream.toTable(Materialized.<Long, OrderDto>as(store)
                .withKeySerde(keySerde)
                .withValueSerde(valueSerde));
    }

}
