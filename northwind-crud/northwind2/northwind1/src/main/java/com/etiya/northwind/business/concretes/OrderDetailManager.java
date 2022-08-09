package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.responses.*;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.sort.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailRepository;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDetailManager implements OrderDetailService {


    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapperService modelMapperService;

    public OrderDetailManager(OrderDetailRepository orderDetailRepository, ModelMapperService modelMapperService) {
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<OrderDetailListResponse> getAlL() {
        List<OrderDetail> result = this.orderDetailRepository.findAll();
        List<OrderDetailListResponse> response = result
                .stream()
                .map(orderDetail -> this.modelMapperService.forResponse().map(orderDetail, OrderDetailListResponse.class))
                .collect(Collectors.toList());
        return response;
    }
    @Override
    public OrderDetailListResponse getOrderDetailById(OrderDetailsId orderDetailsId) {

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailsId).orElse(null);

        if (orderDetail == null) {
            return null;
        }
        return this.modelMapperService.forResponse().map(orderDetail, OrderDetailListResponse.class);

    }


    @Override
    public OrderDetailListResponse createOrderDetail(CreateOrderDetailRequest createOrderDetailRequest) {

        OrderDetail orderDetail = this.modelMapperService.forRequest().map(createOrderDetailRequest, OrderDetail.class);

        orderDetailRepository.save(orderDetail);

        return this.modelMapperService.forResponse().map(orderDetail, OrderDetailListResponse.class);
    }

    @Override
    public void deleteOrderDetailById(OrderDetailsId orderDetailsId) {
        orderDetailRepository.deleteById(orderDetailsId);
    }

    @Override
    public OrderDetailListResponse updateOrderDetail(OrderDetailsId orderDetailsId, UpdateOrderDetailRequest updateOrderDetailRequest) {

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailsId).orElse(null);
        if (orderDetail == null) {
            return null;
        }
        OrderDetail orderDetail2 = this.modelMapperService.forRequest().map(updateOrderDetailRequest, OrderDetail.class);
        orderDetail2.setOrder(orderDetail.getOrder());
        orderDetail2.setProduct(orderDetail.getProduct());

        orderDetailRepository.save(orderDetail2);
        return this.modelMapperService.forResponse().map(orderDetail2, OrderDetailListResponse.class);

    }


    @Override
    public Map<String, Object> findByPageable(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<OrderDetail> result = orderDetailRepository.findAll(pageable);
        List<OrderDetail> orderDetails = result.getContent();
        List<OrderDetailListResponse> orderDetailListResponses = orderDetails.stream().map(orderDetail -> this.modelMapperService.forResponse().map(orderDetail, OrderDetailListResponse.class))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("OrderDetails",orderDetailListResponses);
        response.put("CurrentPage",result.getNumber()+1);
        response.put("Total Items",result.getTotalElements());
        response.put("Total Pages",result.getTotalPages());

        return response;
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize,String property,String type) {
        Pageable pageable=PageRequest.of(pageNumber-1,pageSize, SortingEntities.sortType(property,type));

        Page<OrderDetail>result =orderDetailRepository.findAll(pageable);
        Map<String,Object> response=new HashMap<>();
        response.put("totalElements",result.getTotalElements()) ;
        response.put("totalPages",result.getTotalPages());
        response.put("currentPage",result.getNumber()+1);
        response.put("result",result.getContent().stream().map(orderDetail ->
                this.modelMapperService.forResponse().map(orderDetail, OrderDetailListResponse.class)).collect(Collectors.toList()));

        return response ;
    }
}
