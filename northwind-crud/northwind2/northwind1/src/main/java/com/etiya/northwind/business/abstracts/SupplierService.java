package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.CreateSupplierRequest;
import com.etiya.northwind.business.responses.SupplierListResponse;
import com.etiya.northwind.business.requests.UpdateSupplierRequest;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    List<SupplierListResponse> getAlL();
    SupplierListResponse getSupplierById(String supplierId);

    SupplierListResponse createSupplier(CreateSupplierRequest createSupplierRequest);

    void deleteSupplierById(String supplierId);

    SupplierListResponse updateSupplier(String supplierId, UpdateSupplierRequest updateSupplierRequest);

    Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesSupplierByEntity(int pageNumber, int pageSize, String property, String orElse);
}
