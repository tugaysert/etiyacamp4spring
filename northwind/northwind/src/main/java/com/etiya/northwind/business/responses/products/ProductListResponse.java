package com.etiya.northwind.business.responses.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private int productId;

    private String productName;

    private double unitPrice;

    private int unitsInStock;

    private int categoryId;

    private String categoryName;
}
