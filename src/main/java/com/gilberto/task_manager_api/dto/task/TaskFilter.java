package com.gilberto.task_manager_api.dto.task;

import com.gilberto.task_manager_api.model.enums.TaskPriority;
import com.gilberto.task_manager_api.model.enums.TaskStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskFilter {
    private TaskStatus status;
    private TaskPriority prioridade;
}

