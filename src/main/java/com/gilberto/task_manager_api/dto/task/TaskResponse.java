package com.gilberto.task_manager_api.dto.task;

import com.gilberto.task_manager_api.model.enums.TaskPriority;
import com.gilberto.task_manager_api.model.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private UUID id;
    private String titulo;
    private String descricao;
    private TaskStatus status;
    private TaskPriority prioridade;
    private String categoria;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private UUID userId;
}

