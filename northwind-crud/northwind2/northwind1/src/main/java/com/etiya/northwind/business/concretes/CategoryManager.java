package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.CategoryListResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.sort.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;


    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public List<CategoryListResponse> getAlL() {
        List<Category> result = this.categoryRepository.findAll();
        List<CategoryListResponse> response = result
                .stream()
                .map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class))
                .collect(Collectors.toList());
        return response;
    }


    @Override
    public CategoryListResponse getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return null;
        }
        return this.modelMapperService.forResponse().map(category, CategoryListResponse.class);

    }


    @Override
    public CategoryListResponse createCategory(CreateCategoryRequest createCategoryRequest) {

        Category category = this.modelMapperService.forRequest().map(createCategoryRequest, Category.class);

        categoryRepository.save(category);

        return this.modelMapperService.forResponse().map(category, CategoryListResponse.class);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryListResponse updateCategory(Integer categoryId, UpdateCategoryRequest updateCategoryRequest) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return null;
        }
        Category category2 = this.modelMapperService.forRequest().map(updateCategoryRequest, Category.class);
        category2.setCategoryId(category.getCategoryId());

        categoryRepository.save(category2);
        return this.modelMapperService.forResponse().map(category2, CategoryListResponse.class);

    }


    @Override
    public Map<String, Object> findByPageable(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Category> result = categoryRepository.findAll(pageable);
        List<Category> categorys = result.getContent();
        List<CategoryListResponse> categoryListResponses = categorys.stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("Categories",categoryListResponses);
        response.put("CurrentPage",result.getNumber()+1);
        response.put("Total Items",result.getTotalElements());
        response.put("Total Pages",result.getTotalPages());

        return response;
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize,String property,String type) {
        Pageable pageable=PageRequest.of(pageNumber-1,pageSize, SortingEntities.sortType(property,type));



        Page<Category>result =categoryRepository.findAll(pageable);
        Map<String,Object> response=new HashMap<>();
        response.put("totalElements",result.getTotalElements()) ;
        response.put("totalPages",result.getTotalPages());
        response.put("currentPage",result.getNumber()+1);
        response.put("result",result.getContent().stream().map(category ->
                this.modelMapperService.forResponse().map(category,CategoryListResponse.class)).collect(Collectors.toList()));

        return response ;
    }



}
