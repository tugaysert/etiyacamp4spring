package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.responses.OrderListResponse;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager implements OrderService {


    private final OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<OrderListResponse> getAlL() {
        List<Order> result =  this.orderRepository.findAll();
        List<OrderListResponse> response = new ArrayList<>();
        for (Order order:result) {
            OrderListResponse responseOrder = new OrderListResponse();
            responseOrder.setOrderId(order.getOrderId());
            responseOrder.setCustomerName(order.getCustomer().getContactName() + " "
            + order.getCustomer().getCompanyName());

            responseOrder.setCustomerCustomerId(order.getCustomer().getCustomerId());
            responseOrder.setEmployeeId(order.getEmployee().getEmployeeId());
            responseOrder.setEmployeeName(order.getEmployee().getFirstName() + " " + order.getEmployee().getLastName());
            response.add(responseOrder);
        }
        return response;
    }
}
