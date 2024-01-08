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
        if (rabbitService == null) {
            return "Error: RabbitService is not available";
        }
        try {
            rabbitService.createQueue(userId);
            return "Queue created " + userId;
        } catch (Exception e) {
            return "Error while creating queue for user " + userId + ": " + e.getMessage();
        }
    }
}