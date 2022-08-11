package com.etiya.northwind.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequest {

    @NotNull
    private int orderId;
    @NotNull
    private int productId;
    private int unitPrice;
    private int quantity;
    private int discount;

}
