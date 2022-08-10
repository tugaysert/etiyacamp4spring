package com.etiya.northwind.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequestForOrder {
    private int productId;
    private int unitPrice;
    private int quantity;
    private int discount;
}