package com.gbarwinski.organizerspring.controller.RabbitMqController;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientMq {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/receiveTaskInformation/{userId}")
    public String get(@PathVariable String userId) {
        Object message = rabbitTemplate.receiveAndConvert("taskInformation." + userId);
        if (message == null)
            return "No new messages";
        else {
            return message.toString();
        }
    }
}