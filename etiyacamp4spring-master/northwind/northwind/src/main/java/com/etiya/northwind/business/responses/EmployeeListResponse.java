package com.etiya.northwind.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponse {

    private int employeeId;
    private String firstName;
    private String lastName;
    private List<Integer> orderIdList;

}
