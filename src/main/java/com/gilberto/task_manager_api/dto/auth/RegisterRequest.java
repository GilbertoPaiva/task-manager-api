package com.gilberto.task_manager_api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String senha;
}

