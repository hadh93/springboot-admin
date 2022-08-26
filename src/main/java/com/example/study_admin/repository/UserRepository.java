package com.example.study_admin.repository;

import com.example.study_admin.model.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
