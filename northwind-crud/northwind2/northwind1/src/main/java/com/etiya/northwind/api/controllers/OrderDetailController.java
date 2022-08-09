package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.responses.CreateOrderDetailRequest;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.business.responses.UpdateOrderDetailRequest;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderdetails")

public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/getall")
    public List<OrderDetailListResponse> getAll() {
        return this.orderDetailService.getAlL();
    }

    @GetMapping("/geyById/")
    public OrderDetailListResponse getOrderDetailById(@RequestBody OrderDetailsId orderDetailsId) {
        return orderDetailService.getOrderDetailById(orderDetailsId);
    }

    @PostMapping
    public OrderDetailListResponse createOrderDetail(@RequestBody CreateOrderDetailRequest createOrderDetailRequest)
    {
        return orderDetailService.createOrderDetail(createOrderDetailRequest);
    }

    @DeleteMapping
    public void deleteOrderDetailById(@RequestBody OrderDetailsId orderDetailsId){
        orderDetailService.deleteOrderDetailById(orderDetailsId);
    }

    @PutMapping
    public OrderDetailListResponse updateOrderDetail(@RequestBody OrderDetailsId orderDetailsId, @RequestBody UpdateOrderDetailRequest updateOrderDetailRequest) {
        return orderDetailService.updateOrderDetail(orderDetailsId, updateOrderDetailRequest);
    }

    @GetMapping("/getAllWithPaging")
    public Map<String, Object> getAllWithPaging(@RequestParam int page, @RequestParam int size) {
        return this.orderDetailService.findByPageable(page-1,size);

    }

    @GetMapping("/getallpagesorderbyproperty")
    public Map<String,Object> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String property,@RequestParam Optional<String> type){

        return this.orderDetailService.getAllPagesOrderByEntity(pageNumber,pageSize, property,type.orElse(""));

    }
}
