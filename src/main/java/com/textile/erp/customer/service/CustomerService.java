package com.textile.erp.customer.service;

import com.textile.erp.customer.dto.CustomerDto;
import com.textile.erp.customer.dto.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDto create(CustomerRequest request);

    CustomerDto findById(Long id);

    Page<CustomerDto> findAll(Pageable pageable);

    CustomerDto update(Long id, CustomerRequest request);

    void deactivate(Long id);
}
