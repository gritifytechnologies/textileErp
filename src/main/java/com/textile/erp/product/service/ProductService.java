package com.textile.erp.product.service;

import com.textile.erp.product.dto.ProductDto;
import com.textile.erp.product.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto create(ProductRequest request);

    ProductDto findById(Long id);

    Page<ProductDto> findAll(Pageable pageable);

    Page<ProductDto> findByCategory(Long categoryId, Pageable pageable);

    ProductDto update(Long id, ProductRequest request);

    void deactivate(Long id);
}
