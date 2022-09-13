package com.example.study_admin.service;

import com.example.study_admin.ifs.CrudInterface;
import com.example.study_admin.model.entity.User;
import com.example.study_admin.model.network.Header;
import com.example.study_admin.model.network.request.UserApiRequest;
import com.example.study_admin.model.network.response.UserApiResponse;
import com.example.study_admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. Request Data를 가져온다.
    // 2. User를 생성한다.
    // 3. 생성된 데이터 -> UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. Request Data를 가져온다.
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성.
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user){

        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();
        // Header + data 부분 합쳐 return
        return Header.OK(userApiResponse);
    }

}
