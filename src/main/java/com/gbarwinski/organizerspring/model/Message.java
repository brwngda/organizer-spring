package com.gbarwinski.organizerspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MessageId;
    private Long taskId;
    private Long projectId;
    private String message;
    private Long userId;

}
