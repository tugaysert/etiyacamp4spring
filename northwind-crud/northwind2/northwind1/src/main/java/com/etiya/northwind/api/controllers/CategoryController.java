package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.CategoryListResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorys")

public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/getall")
	public List<CategoryListResponse> getAll() {
		return this.categoryService.getAlL();

	}

	@GetMapping("/{categoryId}")
	public CategoryListResponse getCategoryById(@PathVariable Integer categoryId) {
		return categoryService.getCategoryById(categoryId);
	}
	
	@PostMapping
	public CategoryListResponse createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest)
	{
		return categoryService.createCategory(createCategoryRequest);
	}

	@DeleteMapping("/{categoryId}")
	public void deleteCategoryById(@PathVariable Integer categoryId){
		categoryService.deleteCategoryById(categoryId);
	}

	@PutMapping("/{categoryId}")
	public CategoryListResponse updateCategory(@PathVariable Integer categoryId, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
		return categoryService.updateCategory(categoryId, updateCategoryRequest);
	}

	@GetMapping("/getAllWithPaging")
	public Map<String, Object> getAllWithPaging(@RequestParam int page, @RequestParam int size) {
		return this.categoryService.findByPageable(page-1,size);

	}

	@GetMapping("/getallpagesorderbyproperty")
	public Map<String,Object> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String property,@RequestParam Optional<String> type){

		return this.categoryService.getAllPagesOrderByEntity(pageNumber,pageSize, property,type.orElse(""));

	}
}
