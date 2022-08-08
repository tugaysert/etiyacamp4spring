package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.responses.SupplierListResponse;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Product;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {


    private final SupplierRepository supplierRepository;

    public SupplierManager(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Override
    public List<SupplierListResponse> getAlL() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = new ArrayList<>();
        for (Supplier supplier : result) {
            SupplierListResponse responseSupplier = new SupplierListResponse();
            responseSupplier.setSupplierId(supplier.getSupplierId());
            responseSupplier.setCompanyName(supplier.getCompanyName());
            responseSupplier.setContactName(supplier.getContactName());
            responseSupplier.setProductNameList(
                    supplier.getProductList()
                            .stream()
                            .map(Product::getProductName)
                            .collect(Collectors.toList()));
            response.add(responseSupplier);

        }
        return response;
    }
}
