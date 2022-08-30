package com.example.study_admin.repository;

import com.example.study_admin.StudyAdminApplicationTests;
import com.example.study_admin.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyAdminApplicationTests {

    // Dependency Injection (DI) = 의존성 주입
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        // String sql = insert into user(%s, %s, %d) value(account, email, age);
        // '쿼리'문을 작성하여 DB와 객체를 매칭시켜야 했음
        // JPA는 이 부분에서 편리하다.
        User user = new User();
        // User는 DI로 하지 않는 이유: DI의 기본은 싱글턴이다!
        // User는 커스텀 값이고, 매번 새로운 값이 들어가기 때문에 DI 사용하지 않음.

        // 테스트 유저 객체 user의 값을 지정해준다.
        // 이때 user.setId();는 생략한다. 이유: AI-AutoIncrement 이기 때문
        user.setAccount("TestUser02");
        user.setEmail("TestUser02@gmail.com");
        user.setPhoneNumber("010-2222-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Admin");

        // DB에 새 객체 값을 저장하는 메서드 -> .save()
        User newUser = userRepository.save(user);
        // 잘 저장이 되었는지 콘솔로 찍어보자.
        System.out.println("newUser: "+newUser);
    }

    @Test
    public void read(){
        Optional<User> user;
        user = userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            System.out.println("user: "+selectUser);
            System.out.println("user: "+selectUser.getEmail());
        });
    }

    @Test
    public void update(){
        Optional<User> user;
        user = userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    public void delete() {

    }

}
