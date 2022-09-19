package com.example.study_admin.repository;

import com.example.study_admin.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
