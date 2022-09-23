package com.example.study_admin.service;

import com.example.study_admin.ifs.CrudInterface;
import com.example.study_admin.model.entity.AdminUser;
import com.example.study_admin.model.network.Header;
import com.example.study_admin.model.network.request.AdminUserApiRequest;
import com.example.study_admin.model.network.response.AdminUserApiResponse;
import com.example.study_admin.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();
        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .registeredAt(body.getRegisteredAt())
                .unregisteredAt(body.getUnregisteredAt())
                .loginFailCount(0)
                .build();
        AdminUser newAdminUser = baseRepository.save(adminUser);
        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map( adminUser -> {
                    adminUser
                            .setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return baseRepository.save(adminUser);
                })
                .map(this::response)
                .orElseGet( () -> Header.ERROR("데이터 없음") );
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private Header<AdminUserApiResponse> response(AdminUser request){
        AdminUserApiResponse body = AdminUserApiResponse.builder()
                .id(request.getId())
                .account(request.getAccount())
                .password(request.getPassword())
                .status(request.getStatus())
                .role(request.getRole())
                .lastLoginAt(request.getLastLoginAt())
                .registeredAt(request.getRegisteredAt())
                .unregisteredAt(request.getUnregisteredAt())
                .build();
        return Header.OK(body);

    }

}
