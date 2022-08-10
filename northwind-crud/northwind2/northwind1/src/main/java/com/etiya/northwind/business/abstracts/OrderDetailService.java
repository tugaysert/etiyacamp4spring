package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.CreateOrderDetailRequest;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.business.requests.UpdateOrderDetailRequest;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import com.etiya.northwind.entities.concretes.Product;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    List<OrderDetailListResponse> getAlL();

    OrderDetailListResponse getOrderDetailById(Integer orderId, Integer productId);

    OrderDetailListResponse createOrderDetail(CreateOrderDetailRequest createOrderDetailRequest);


    void deleteOrderDetailById(Integer orderId, Integer productId);

    OrderDetailListResponse updateOrderDetail(OrderDetailsId orderDetailsId, UpdateOrderDetailRequest updateOrderDetailRequest);

    Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String property, String orElse);
}
