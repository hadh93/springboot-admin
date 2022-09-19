package com.example.study_admin.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserApiRequest {
    private Long id;
    private String account;
    private String password;
    private String status;
    private String role;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
}
