package com.etiya.northwind.business.responses;

import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import com.etiya.northwind.entities.concretes.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequest {



    private OrderRequestForOrderDetail orderRequestForOrderDetail;
    private ProductRequestForOrderDetail productRequestForOrderDetail;
    private int unitPrice;
    private int quantity;
    private int discount;

}
