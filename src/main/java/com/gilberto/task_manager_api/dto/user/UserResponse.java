package com.gilberto.task_manager_api.dto.user;

import com.gilberto.task_manager_api.model.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String nome;
    private String email;
    private UserRole role;
    private LocalDateTime createdAt;
}

