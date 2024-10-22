package com.codealpha.resoft_be.common.config;

import java.util.Set;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReactiveKafkaConfig {
    @Bean
    public KafkaSender<String, String> kafkaSender() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "13.209.21.155:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        SenderOptions<String, String> senderOptions = SenderOptions.create(configProps);
        return KafkaSender.create(senderOptions);
    }

    @Bean
    public KafkaReceiver<String, String> kafkaReceiver() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "13.209.21.155:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group"); // Consumer 그룹 ID 설정
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // 메시지를 어디서부터 읽을지 설정

        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.<String, String>create(consumerProps)
                .subscription(Set.of("ai-responses")); // 구독할 토픽 설정

        return KafkaReceiver.create(receiverOptions);
    }
}
