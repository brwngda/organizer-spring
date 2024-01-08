package com.gbarwinski.organizerspring.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitService {

    private final AmqpAdmin amqpAdmin;
    private final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitService.class);
    private final DirectExchange directExchange;

    public void createQueue(String userId) {
        String queueName = "taskInformation." + userId;
        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(queueName);
        amqpAdmin.declareBinding(binding);
    }

    public boolean isExchangeExists(String exchange) {
        return rabbitTemplate.execute(channel -> {
            try (channel) {
                return channel.exchangeDeclarePassive(exchange);
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Exchange '" + exchange + "' does not exist");
                }
                return null;
            }
        }) != null;
    }
}