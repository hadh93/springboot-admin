package com.example.study_admin.model.network.request;

import com.example.study_admin.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
    private Long id;
    private String account;
    private String password;
    private UserStatus status;
    private String email;
    private String phoneNumber;

    public LocalDateTime registeredAt;
    public LocalDateTime unregisteredAt;

}
// Request-Response를 따로 관리하는 이유: 값이 동일하지 않을 수 있으며, 목적이 다르므로 별개의 클래스로 구분한다.
// e.g. password는 request부에서는 평문, response부에서는 마스킹되어 받게 됨