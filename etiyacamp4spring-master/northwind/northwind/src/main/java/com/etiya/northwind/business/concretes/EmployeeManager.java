package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.responses.EmployeeListResponse;
import com.etiya.northwind.dataAccess.abstracts.EmployeeRepository;
import com.etiya.northwind.entities.concretes.Employee;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeManager implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<EmployeeListResponse> getAlL() {
        List<Employee> result = this.employeeRepository.findAll();
        List<EmployeeListResponse> response = new ArrayList<>();
        for (Employee employee : result) {
            EmployeeListResponse responseEmployee = new EmployeeListResponse();
            responseEmployee.setEmployeeId(employee.getEmployeeId());
            responseEmployee.setFirstName(employee.getFirstName());
            responseEmployee.setLastName(employee.getLastName());
            responseEmployee.setOrderIdList(
                    employee.getOrderList()
                            .stream()
                            .map(Order::getOrderId)
                            .collect(Collectors.toList()));


            response.add(responseEmployee);
        }
        return response;
    }
}
