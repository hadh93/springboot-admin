package com.example.study_admin.service;

import com.example.study_admin.ifs.CrudInterface;
import com.example.study_admin.model.entity.Partner;
import com.example.study_admin.model.network.Header;
import com.example.study_admin.model.network.request.PartnerApiRequest;
import com.example.study_admin.model.network.response.PartnerApiResponse;
import com.example.study_admin.repository.CategoryRepository;
import com.example.study_admin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();
        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(body.getRegisteredAt())
                .category(categoryRepository.getReferenceById(body.getCategoryId()))
                .build();
        Partner newPartner = baseRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map(partner -> {
                    partner
                            .setStatus(body.getStatus())
                            .setAddress(body.getAddress())
                            .setCallCenter(body.getCallCenter())
                            .setPartnerNumber(body.getPartnerNumber())
                            .setBusinessNumber(body.getBusinessNumber())
                            .setCeoName(body.getCeoName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            .setCategory(categoryRepository.getReferenceById(body.getCategoryId()));
                    return partner;
                })
                .map(newPartner -> {
                    baseRepository.save(newPartner);
                    return newPartner;
                })
                .map(this::response)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner){
        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partner.getId())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();
        return Header.OK(body);
    }

}
