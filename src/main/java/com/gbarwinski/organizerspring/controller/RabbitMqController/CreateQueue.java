package com.gbarwinski.organizerspring.controller.RabbitMqController;

import com.gbarwinski.organizerspring.service.RabbitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateQueue {

    private final RabbitService rabbitService;

    @GetMapping("/createQueue")
    public String create(@RequestParam(value = "userId") String userId) {
        rabbitService.createQueue(userId);
        return "Queue created" + userId;
    }
}