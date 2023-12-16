package com.gbarwinski.organizerspring.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.gbarwinski.organizerspring.config.RabbitMqConfig.getDirectExchange;

@Service
@RequiredArgsConstructor
public class RabbitService {

    private final AmqpAdmin amqpAdmin;
    private final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitService.class);
    private final DirectExchange newDirectExchange;

    public void createQueue(String userId) {
        String exchange = "organizer";
        DirectExchange organizer;
        boolean exists = isExchangeExists(exchange);
        if (exists) {
            organizer = getDirectExchange();
        } else {
            organizer = newDirectExchange;
        }
        String queueName = "taskInformation." + userId;
        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(organizer).with(queueName);
        amqpAdmin.declareBinding(binding);
    }

    public boolean isExchangeExists(String exchange) {
        return rabbitTemplate.execute(new ChannelCallback<AMQP.Exchange.DeclareOk>() {
            @Override
            public AMQP.Exchange.DeclareOk doInRabbit(Channel channel) throws Exception {
                try (channel) {
                    return channel.exchangeDeclarePassive(exchange);
                } catch (Exception e) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Exchange '" + exchange + "' does not exist");
                    }
                    return null;
                }
            }
        }) != null;
    }
}