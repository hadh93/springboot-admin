package com.example.study_admin.model.network.response;

import com.example.study_admin.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {
    private Long id;
    private String account;
    private String password; // 마스킹 되어 내려갈 예정
    private UserStatus status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;

    private List<OrderGroupApiResponse> orderGroupApiResponseList;
}