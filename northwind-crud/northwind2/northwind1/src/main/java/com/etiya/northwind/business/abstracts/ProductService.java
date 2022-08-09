package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.CreateProductRequest;
import com.etiya.northwind.business.responses.ProductListResponse;
import com.etiya.northwind.business.responses.ProductListResponse;
import com.etiya.northwind.business.responses.UpdateProductRequest;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductListResponse> getAlL();

    ProductListResponse getProductById(String productId);

    ProductListResponse createProduct(CreateProductRequest createProductRequest);

    void deleteProductById(String productId);

    ProductListResponse updateProduct(String productId, UpdateProductRequest updateProductRequest);

    Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesProductByEntity(int pageNumber, int pageSize, String property, String orElse);
}
