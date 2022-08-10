package com.etiya.northwind.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequest {

    private Integer supplierId;
    private String companyName;
    private String contactName;
}