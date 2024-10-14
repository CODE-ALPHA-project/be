package com.codealpha.resoft_be.domain.message.service.strategy;

import lombok.Getter;

import java.util.EnumMap;

@Getter
public class MessageStrategyMap {
    private final EnumMap<MessageStrategyType, MessageStrategy> strategyMap;

    // Private constructor to enforce usage of the builder
    private MessageStrategyMap(EnumMap<MessageStrategyType, MessageStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public MessageStrategy getStrategy(MessageStrategyType type) {
        return strategyMap.get(type);
    }

    public static MessageStrategyMapBuilder builder() {
        return new MessageStrategyMapBuilder();
    }

    public static class MessageStrategyMapBuilder {
        private EnumMap<MessageStrategyType, MessageStrategy> strategyMap; // No final here

        // Constructor initializes the EnumMap
        public MessageStrategyMapBuilder() {
            strategyMap = new EnumMap<>(MessageStrategyType.class); // Initialize here
        }

        public MessageStrategyMapBuilder addStrategy(MessageStrategyType type, MessageStrategy strategy) {
            strategyMap.put(type, strategy);  // Add the strategy to the EnumMap
            return this;  // Return this for method chaining
        }

        public MessageStrategyMap build() {
            return new MessageStrategyMap(strategyMap);  // Return a new MessageStrategyMap instance
        }
    }
}
