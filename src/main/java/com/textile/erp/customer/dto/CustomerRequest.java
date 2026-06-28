package com.textile.erp.customer.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "Customer name is required")
    @Size(max = 200)
    private String name;

    @Size(max = 150)
    private String contactPerson;

    @Email(message = "Email must be valid")
    private String email;

    @Size(max = 30)
    private String phone;

    @Size(max = 500)
    private String address;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String taxNumber;

    @DecimalMin(value = "0.0", message = "Credit limit must be positive")
    private BigDecimal creditLimit;
}
