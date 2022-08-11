package com.etiya.northwind.business.requests;

import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotNull
    private Integer orderId;
    @NotNull
    private String customerId;
    @NotNull
    private String employeeId;

}
