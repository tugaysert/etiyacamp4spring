package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getAlL();
}
