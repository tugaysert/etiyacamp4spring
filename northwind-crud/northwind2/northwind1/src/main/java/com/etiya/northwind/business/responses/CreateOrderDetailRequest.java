package com.etiya.northwind.business.responses;

import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.Product;
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

    private Integer orderId;
    private Integer productId;
    private int unitPrice;
    private int quantity;
    private int discount;
}
