package com.etiya.northwind.business.responses;

import com.etiya.northwind.entities.concretes.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResponse {

    private String customerId;
    private String companyName;
    private String contactName;



}
