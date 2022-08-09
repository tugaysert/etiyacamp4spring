package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.abstracts.UpdateOrderRequest;
import com.etiya.northwind.business.responses.*;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.sort.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {


    private final OrderRepository orderRepository;
    private final ModelMapperService modelMapperService;

    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public List<OrderListResponse> getAlL() {
        List<Order> result =  this.orderRepository.findAll();
        List<OrderListResponse> response = result
                .stream()
                .map(order -> this.modelMapperService.forResponse().map(order, OrderListResponse.class))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            manuelNameMapping(response.get(i),result.get(i));
        }

        return response;
    }

    @Override
    public OrderListResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(Integer.valueOf(orderId)).orElse(null);

        if (order == null) {
            return null;
        }
        OrderListResponse orderListResponse =  this.modelMapperService.forResponse().map(order, OrderListResponse.class);
        manuelNameMapping(orderListResponse, order);
        return orderListResponse;

    }
    public void manuelNameMapping(OrderListResponse orderListResponse, Order order){
        orderListResponse.setCustomerName(order.getCustomer().getCompanyName()
                + " and " + order.getCustomer().getContactName());
        orderListResponse.setEmployeeName(order.getEmployee().getFirstName()
                + " " + order.getEmployee().getLastName());
    }


    @Override
    public OrderListResponse createOrder(CreateOrderRequest createOrderRequest) {

        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        orderRepository.save(order);
        OrderListResponse orderListResponse = this.modelMapperService.forResponse().map(order, OrderListResponse.class);
        manuelNameMapping(orderListResponse, order);
        return orderListResponse;
    }

    @Override
    public void deleteOrderById(String orderId) {
        orderRepository.deleteById(Integer.valueOf(orderId));
    }

    @Override
    public OrderListResponse updateOrder(String orderId, UpdateOrderRequest updateOrderRequest) {

        Order order = orderRepository.findById(Integer.valueOf(orderId)).orElse(null);
        if (order == null) {
            return null;
        }
        Order order2 = this.modelMapperService.forRequest().map(updateOrderRequest, Order.class);
        order2.setOrderId(order.getOrderId());
        order2.setCustomer(order.getCustomer());
        order2.setEmployee(order.getEmployee());

        orderRepository.save(order2);
        return this.modelMapperService.forResponse().map(order2, OrderListResponse.class);

    }


    @Override
    public Map<String, Object> findByPageable(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Order> result = orderRepository.findAll(pageable);
        List<Order> orders = result.getContent();
        List<OrderListResponse> orderListResponses =
                orders
                        .stream()
                        .map(order ->
                                this.modelMapperService.forResponse().map(order, OrderListResponse.class))
                .collect(Collectors.toList());


        Map<String, Object> response = new HashMap<>();
        response.put("Orders",orderListResponses);
        response.put("CurrentPage",result.getNumber()+1);
        response.put("Total Items",result.getTotalElements());
        response.put("Total Pages",result.getTotalPages());

        return response;
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize,String property,String type) {
        Pageable pageable=PageRequest.of(pageNumber-1,pageSize, SortingEntities.sortType(property,type));



        Page<Order>result =orderRepository.findAll(pageable);
        List<OrderListResponse> orderListResponses =
                result.getContent()
                        .stream()
                        .map(order ->
                                this.modelMapperService.forResponse().map(order, OrderListResponse.class))
                        .collect(Collectors.toList());
        for (int i = 0; i < orderListResponses.size(); i++) {
            if(orderListResponses.get(i).getOrderId()==result.getContent().get(i).getOrderId()) {
                manuelNameMapping(orderListResponses.get(i), result.getContent().get(i));
            }
        }

        Map<String,Object> response=new HashMap<>();
        response.put("totalElements",result.getTotalElements()) ;
        response.put("totalPages",result.getTotalPages());
        response.put("currentPage",result.getNumber()+1);
        response.put("result", orderListResponses);

        return response ;
    }
}
