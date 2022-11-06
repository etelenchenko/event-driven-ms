package com.etelenchenko.productsservice.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommandConfig {

    private final CommandBus commandBus;

    public CommandConfig(CommandBus commandBus, List<MessageDispatchInterceptor<CommandMessage<?>>> commandInterceptors) {
        this.commandBus = commandBus;
        commandInterceptors.forEach(commandBus::registerDispatchInterceptor);
    }
}
