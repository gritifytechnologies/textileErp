package com.textile.erp.customer.service;

import com.textile.erp.customer.dto.CustomerDto;
import com.textile.erp.customer.dto.CustomerRequest;
import com.textile.erp.customer.entity.Customer;
import com.textile.erp.customer.mapper.CustomerMapper;
import com.textile.erp.customer.repository.CustomerRepository;
import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.security.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper     customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper     = customerMapper;
    }

    @Override
    public CustomerDto create(CustomerRequest request) {
        Long tenantId = TenantContext.getTenantId();
        if (request.getEmail() != null && customerRepository.existsByEmailAndTenantId(request.getEmail(), tenantId)) {
            throw new DuplicateResourceException("Customer", "email", request.getEmail());
        }
        Customer customer = customerMapper.toEntity(request);
        customer.setTenantId(tenantId);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findById(Long id) {
        return customerMapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDto> findAll(Pageable pageable) {
        return customerRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(customerMapper::toDto);
    }

    @Override
    public CustomerDto update(Long id, CustomerRequest request) {
        Customer customer = getOrThrow(id);
        customerMapper.updateEntity(request, customer);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public void deactivate(Long id) {
        Customer customer = getOrThrow(id);
        customer.setActive(false);
        customerRepository.save(customer);
    }

    private Customer getOrThrow(Long id) {
        return customerRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }
}
