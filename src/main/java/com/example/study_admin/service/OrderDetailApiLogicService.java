package com.example.study_admin.service;

import com.example.study_admin.ifs.CrudInterface;
import com.example.study_admin.model.entity.OrderDetail;
import com.example.study_admin.model.network.Header;
import com.example.study_admin.model.network.request.OrderDetailApiRequest;
import com.example.study_admin.model.network.request.OrderGroupApiRequest;
import com.example.study_admin.model.network.response.OrderDetailApiResponse;
import com.example.study_admin.repository.ItemRepository;
import com.example.study_admin.repository.OrderDetailRepository;
import com.example.study_admin.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {



    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();
        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())

                .orderGroup(orderGroupRepository.getReferenceById(body.getOrderGroupId()))
                .item(itemRepository.getReferenceById(body.getItemId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        Optional<OrderDetail> optional = baseRepository.findById(id);
        return optional.map(orderDetail -> response(orderDetail))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map(orderDetail -> {
                    orderDetail
                            .setStatus(body.getStatus())
                            .setArrivalDate(body.getArrivalDate())
                            .setQuantity(body.getQuantity())
                            .setTotalPrice(body.getTotalPrice())
                            .setOrderGroup(orderGroupRepository.getReferenceById(body.getOrderGroupId()))
                            .setItem(itemRepository.getReferenceById(body.getItemId()));
                    return orderDetail;
                })
                .map(changedOrderDetail -> baseRepository.save(changedOrderDetail))
                .map(this::response)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map( orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail){
        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();
        return Header.OK(body);
    }
}
