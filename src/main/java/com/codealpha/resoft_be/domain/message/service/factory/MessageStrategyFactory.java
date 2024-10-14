package com.codealpha.resoft_be.domain.message.service.factory;

import com.codealpha.resoft_be.domain.message.service.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessageStrategyFactory {

    private final MessageStrategyMap strategyMap;
    public MessageStrategy createMessageStrategy(MessageStrategyType type){
        return strategyMap.getStrategy(type);
    }
}
