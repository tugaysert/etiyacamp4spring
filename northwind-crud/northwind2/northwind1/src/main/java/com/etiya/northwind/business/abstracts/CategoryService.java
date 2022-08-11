package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.CategoryListResponse;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<CategoryListResponse> getAlL();

	CategoryListResponse getCategoryById(Integer customerId);

	CategoryListResponse createCategory(CreateCategoryRequest createCategoryRequest);

	void deleteCategoryById(Integer customerId);

	CategoryListResponse updateCategory(Integer customerId, UpdateCategoryRequest updateCategoryRequest);

	Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String property, String orElse);
}




