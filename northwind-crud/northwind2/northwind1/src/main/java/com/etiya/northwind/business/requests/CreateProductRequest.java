package com.etiya.northwind.business.requests;

import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    @NotNull
    private Integer productId;
    private String productName;
    private double unitPrice;
    private int unitsInStock;
    private String categoryId;
    private String supplierId;
}

