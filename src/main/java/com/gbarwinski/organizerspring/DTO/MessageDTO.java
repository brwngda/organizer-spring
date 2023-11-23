package com.gbarwinski.organizerspring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long taskId;
    private String message;
    private Long userId;
    private String taskName;
}
