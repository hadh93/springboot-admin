package com.example.study_admin.controller.api;

import com.example.study_admin.controller.CrudController;
import com.example.study_admin.ifs.CrudInterface;
import com.example.study_admin.model.entity.OrderDetail;
import com.example.study_admin.model.network.Header;
import com.example.study_admin.model.network.request.OrderDetailApiRequest;
import com.example.study_admin.model.network.request.OrderGroupApiRequest;
import com.example.study_admin.model.network.response.OrderDetailApiResponse;
import com.example.study_admin.model.network.response.OrderGroupApiResponse;
import com.example.study_admin.service.OrderDetailApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
}
