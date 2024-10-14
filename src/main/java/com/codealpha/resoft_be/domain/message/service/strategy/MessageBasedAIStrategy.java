package com.codealpha.resoft_be.domain.message.service.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageBasedAIStrategy implements MessageStrategy {
private final KafkaSender<String, String> kafkaSender;
    private static final String TOPIC = "ai-messages";

    @Override
    public Mono<Void> sendMessage(String target, String message) {
        log.info("Sending message: {}", message);
        return kafkaSender
                .send(Mono.just(createSenderRecord(target, message)))
                .doOnNext(senderResult -> log.info("Message sent successfully to topic: {}, partition: {}, offset: {}",
                        senderResult.recordMetadata().topic(),
                        senderResult.recordMetadata().partition(),
                        senderResult.recordMetadata().offset()))
                .doOnError(e -> log.error("Error sending message: {}", e.getMessage()))
                .then();
    }

    private SenderRecord<String, String, String> createSenderRecord(String target, String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC, target, message);
        return SenderRecord.create(producerRecord, null);
    }
}
