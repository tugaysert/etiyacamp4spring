package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.responses.CustomerListResponse;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerListResponse> getAlL() {
        List<Customer> result = this.customerRepository.findAll();
        List<CustomerListResponse> response = new ArrayList<>();
        for (Customer customer : result) {
            CustomerListResponse responseCustomer = new CustomerListResponse();
            responseCustomer.setCustomerId(customer.getCustomerId());
            responseCustomer.setCompanyName(customer.getCompanyName());
            responseCustomer.setContactName(customer.getContactName());
            responseCustomer.setOrderIdList(
                    customer.getOrderList()
                            .stream()
                            .map(Order::getOrderId)
                            .collect(Collectors.toList()));
            response.add(responseCustomer);

        }
        return response;
    }
}
