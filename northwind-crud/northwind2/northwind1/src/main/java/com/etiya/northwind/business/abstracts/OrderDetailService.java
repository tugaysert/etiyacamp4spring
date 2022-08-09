package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.CreateOrderDetailRequest;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.business.responses.UpdateOrderDetailRequest;
import com.etiya.northwind.entities.concretes.OrderDetailsId;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    List<OrderDetailListResponse> getAlL();

    OrderDetailListResponse getOrderDetailById(OrderDetailsId orderDetailsId);

    OrderDetailListResponse createOrderDetail(CreateOrderDetailRequest createOrderDetailRequest);

    void deleteOrderDetailById(OrderDetailsId orderDetailsId);

    OrderDetailListResponse updateOrderDetail(OrderDetailsId orderDetailsId, UpdateOrderDetailRequest updateOrderDetailRequest);

    Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String property, String orElse);
}
