package com.etiya.northwind.business.requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
	@NotNull
	private String customerId;
	@NotNull
	@NotBlank
	private String companyName;
	@NotNull
	@NotBlank
	private String contactName;

}
