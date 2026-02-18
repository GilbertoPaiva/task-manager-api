package com.gilberto.task_manager_api.dto.task;

import com.gilberto.task_manager_api.model.enums.TaskPriority;
import com.gilberto.task_manager_api.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotBlank
    private String titulo;

    private String descricao;

    private TaskStatus status;

    private TaskPriority prioridade;

    private String categoria;

    private LocalDate dueDate;
}

