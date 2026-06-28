package com.textile.erp.customer.mapper;

import com.textile.erp.customer.dto.CustomerDto;
import com.textile.erp.customer.dto.CustomerRequest;
import com.textile.erp.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerRequest request);

    void updateEntity(CustomerRequest request, @MappingTarget Customer customer);
}
