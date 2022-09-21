package com.example.study_admin.model.entity;

import com.example.study_admin.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// 변수명에 Low camel case 사용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity // == table
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name="account")와 동일. 변수명과 동일하다면 표기가 필요하지 않다.
    private String account;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    // Auditor에 의해 자동 관리되는 속성들
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // 1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // OrderGroup의 @ManyToOne 어노테이션의 변수명과 동일해야 함!
    private List<OrderGroup> orderGroupList;
/*
    // 1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;*/
}
