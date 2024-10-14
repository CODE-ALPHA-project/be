package com.codealpha.resoft_be.common.config;

import com.codealpha.resoft_be.domain.message.service.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MessageConfig {
    private final  MessageBasedAIStrategy aiStrategy;
    private final MessageBasedHumanStrategy humanStrategy;

    @Bean
    public MessageStrategyMap messageStrategyMap(){
       return MessageStrategyMap.builder()
               .addStrategy(MessageStrategyType.AI, aiStrategy)
               .addStrategy(MessageStrategyType.HUMAN, humanStrategy)
               .build();
    }
}
