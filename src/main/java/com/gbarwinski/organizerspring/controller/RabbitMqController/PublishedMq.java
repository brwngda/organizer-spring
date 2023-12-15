package com.gbarwinski.organizerspring.controller.RabbitMqController;

import com.gbarwinski.organizerspring.DTO.MessageDTO;
import com.gbarwinski.organizerspring.model.Message;
import com.gbarwinski.organizerspring.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Set;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PublishedMq {

    private final MessageService messageService;
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    @GetMapping("/sendTaskInformation/{userId}")
    public void saveMessageAndSendToAssignedUsers(@PathVariable(value = "userId") String userId, @RequestParam(value = "taskId") String taskId, @RequestParam(value = "update") String update) {
        Long taskIdLong = Long.parseLong(taskId);
        Long userIdLong = Long.parseLong(userId);
        MessageDTO messageDTO = MessageDTO.builder()
                .taskId(taskIdLong)
                .userId(userIdLong)
                .build();
        Message messageReadyToSend = messageService.saveAndGetReadyMessage(messageDTO, update);
        Set<Long> assignedUsersIdToProject = messageService.getAssignedUsersToProject(messageDTO);
        assignedUsersIdToProject.forEach(Id -> rabbitTemplate.convertAndSend("taskInformation." + Id, messageReadyToSend.getMessage()));
    }

    @GetMapping("/newInformationCounter/{userId}")
    public Integer getNewInformationCounter(@PathVariable(value = "userId") String userId) {
        String queueName = "taskInformation." + userId;
        try {
            Properties props = amqpAdmin.getQueueProperties(queueName);
            assert props != null;
            return Integer.parseInt(props.get("QUEUE_MESSAGE_COUNT").toString());
        } catch (Exception e) {
            log.error("No rabbitmq connection", e);
        }
        return 1;
    }
}