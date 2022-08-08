package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailRepository;
import com.etiya.northwind.entities.concretes.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailManager implements OrderDetailService {


    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailManager(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailListResponse> getAlL() {
        List<OrderDetail> result = this.orderDetailRepository.findAll();
        List<OrderDetailListResponse> response = new ArrayList<>();
        for (OrderDetail orderDetail : result) {
            OrderDetailListResponse responseOrderDetail = new OrderDetailListResponse();
            responseOrderDetail.setOrderId(orderDetail.getOrder().getOrderId());
            responseOrderDetail.setProductId(orderDetail.getProduct().getProductId());
            responseOrderDetail.setDiscount(orderDetail.getDiscount());
            responseOrderDetail.setQuantity(orderDetail.getQuantity());
            responseOrderDetail.setDiscount(orderDetail.getDiscount());

            response.add(responseOrderDetail);
        }
        return response;
    }
}
