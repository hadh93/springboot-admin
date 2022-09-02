package com.example.study_admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user", "item"})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // N:1
    // 주의: OrderDetail의 입장에서 생각할 것!
    @ManyToOne
    private User user; // hibernate가 user_id를 찾아감.

    // N:1
    @ManyToOne
    private Item item;
}
