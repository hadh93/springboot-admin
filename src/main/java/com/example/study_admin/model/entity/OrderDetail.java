package com.example.study_admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"orderGroup", "item"})
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    // Auditor에 의해 자동 관리되는 속성들
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // OrderDetail N: 1 Item
    @ManyToOne
    private Item item; // FK -> 객체로 변경

    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup; // FK -> 객체로 변경


/*    // N:1
    // 주의: OrderDetail의 입장에서 생각할 것!
    @ManyToOne
    private User user; // hibernate가 user_id를 찾아감.

    // N:1
    @ManyToOne
    private Item item;*/
}
