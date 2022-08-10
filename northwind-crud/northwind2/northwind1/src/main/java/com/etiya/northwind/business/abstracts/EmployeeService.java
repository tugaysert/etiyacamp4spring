package com.etiya.northwind.business.abstracts;


import com.etiya.northwind.business.requests.CreateEmployeeRequest;
import com.etiya.northwind.business.responses.EmployeeListResponse;
import com.etiya.northwind.business.requests.UpdateEmployeeRequest;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<EmployeeListResponse> getAlL();

    EmployeeListResponse getEmployeeById(String employeeId);

    EmployeeListResponse createEmployee(CreateEmployeeRequest createEmployeeRequest);

    void deleteEmployeeById(String employeeId);

    EmployeeListResponse updateEmployee(String employeeId, UpdateEmployeeRequest updateEmployeeRequest);

    Map<String, Object> findByPageable(int page, int size);

    Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String property, String orElse);
}
