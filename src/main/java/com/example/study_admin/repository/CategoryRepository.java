package com.example.study_admin.repository;

import com.example.study_admin.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByType(String type); // SELECT * FROM category WHERE type = 'COMPUTER'
}
