package com.example.study_admin.repository;

import com.example.study_admin.model.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber); // 가장 최근 것이 return됨
    /*Optional<User> findByAccount(String account); // SELECT * FROM user WHERE account = ? << "test03", "test04" ...
    Optional<User> findByEmail(String email); // SELECT * FROM user WHERE email = ? << "testuser03@gmail.com" ...
    Optional<User> findByAccountAndEmail(String account, String email); // SELECT * FROM user WHERE account = ? AND email = ?*/
}
