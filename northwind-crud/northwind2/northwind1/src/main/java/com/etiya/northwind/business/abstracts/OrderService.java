package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.*;
import com.etiya.northwind.business.responses.OrderListResponse;


import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderListResponse> getAlL();

    OrderListResponse getOrderById(String orderId);

    OrderListResponse createOrder(CreateCombineOfOrderRequestAndOrderDetailRequest createCombineOfOrderRequestAndOrderDetailRequest );


    void deleteOrderById(String orderId);

    OrderListResponse updateOrder(String orderId, UpdateOrderRequest updateOrderRequest);

    Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String property, String orElse);
}
