package com.example.study_admin.repository;

import com.example.study_admin.StudyAdminApplicationTests;
import com.example.study_admin.model.entity.Item;
import com.example.study_admin.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.junit.jupiter.api.Assertions;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyAdminApplicationTests {

    // Dependency Injection (DI) = 의존성 주입
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-2222-2222";
        LocalDateTime registeredAt = LocalDateTime.now();


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        // @Builder 어노테이션을 사용하면, 일부 매개변수만 넣은 생성자를 만들 수 있다.
        // 참고: @Accessors(chain = true) 도 유사한 패턴을 만들 수 있다.
        /*User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build(); // phonenumber와 registeredat가 없다!*/


        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");


        // '체인' 패턴으로 객체 값 수정 시, 다음과 같은 코드 용례가 가능함.
//        user
//                .setEmail("")
//                .setPhoneNumber("")
//                .setStatus("");
//
//        User u = new User().setAccount().setEmail().setPassword();

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("----------------주문묶음----------------");
            System.out.println("수령인: " + orderGroup.getRevName());
            System.out.println("수령지: " + orderGroup.getRevAddress());
            System.out.println("총 금액: " + orderGroup.getTotalPrice());
            System.out.println("총 수량: " + orderGroup.getTotalQuantity());

            System.out.println("----------------주문상태----------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품: " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태: " + orderDetail.getStatus());
                System.out.println("도착예정일자: " + orderDetail.getArrivalDate());



            });
        });
        Assertions.assertNotNull(user);
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assertions.assertTrue(user.isPresent()); // true가 나와야 정상

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });
        Optional<User> deletedUser = userRepository.findById(1L);

        if(deletedUser.isPresent()){
            System.out.println("데이터 존재: "+deletedUser.get());
        } else{
            System.out.println("데이터 삭제 완료, 데이터 없음.");
        }

        Assertions.assertFalse(deletedUser.isPresent()); // false가 나와야 정상

    }

}
