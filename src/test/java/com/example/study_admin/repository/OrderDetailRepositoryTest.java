package com.example.study_admin.repository;

import com.example.study_admin.StudyAdminApplicationTests;
import com.example.study_admin.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyAdminApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());

        // 어떤 사람?
        orderDetail.setUserId(1L);

        // 어떤 상품?
        orderDetail.setItemId(2L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }
}
