package com.example.study_admin.repository;

import com.example.study_admin.StudyAdminApplicationTests;
import com.example.study_admin.model.entity.OrderDetail;
import com.example.study_admin.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends StudyAdminApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){

    }

}
