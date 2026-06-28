package com.textile.erp.product.service;

import com.textile.erp.category.entity.Category;
import com.textile.erp.category.repository.CategoryRepository;
import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.product.dto.ProductDto;
import com.textile.erp.product.dto.ProductRequest;
import com.textile.erp.product.entity.Product;
import com.textile.erp.product.mapper.ProductMapper;
import com.textile.erp.product.repository.ProductRepository;
import com.textile.erp.security.TenantContext;
import com.textile.erp.unit.entity.Unit;
import com.textile.erp.unit.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository  productRepository;
    private final CategoryRepository categoryRepository;
    private final UnitRepository     unitRepository;
    private final ProductMapper      productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              UnitRepository unitRepository,
                              ProductMapper productMapper) {
        this.productRepository  = productRepository;
        this.categoryRepository = categoryRepository;
        this.unitRepository     = unitRepository;
        this.productMapper      = productMapper;
    }

    @Override
    public ProductDto create(ProductRequest request) {
        Long tenantId = TenantContext.getTenantId();
        if (productRepository.existsByCodeAndTenantId(request.getCode(), tenantId)) {
            throw new DuplicateResourceException("Product", "code", request.getCode());
        }
        Category category = categoryRepository.findByIdAndTenantId(request.getCategoryId(), tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));
        Unit unit = unitRepository.findByIdAndTenantId(request.getUnitId(), tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit", "id", request.getUnitId()));

        Product product = Product.builder()
                .tenantId(tenantId)
                .category(category)
                .unit(unit)
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .buyingPrice(request.getBuyingPrice())
                .sellingPrice(request.getSellingPrice())
                .reorderLevel(request.getReorderLevel())
                .active(true)
                .build();

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return productMapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findAllByTenantIdAndCategoryId(TenantContext.getTenantId(), categoryId, pageable)
                .map(productMapper::toDto);
    }

    @Override
    public ProductDto update(Long id, ProductRequest request) {
        Long     tenantId = TenantContext.getTenantId();
        Product  product  = getOrThrow(id);
        Category category = categoryRepository.findByIdAndTenantId(request.getCategoryId(), tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));
        Unit unit = unitRepository.findByIdAndTenantId(request.getUnitId(), tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit", "id", request.getUnitId()));

        product.setCategory(category);
        product.setUnit(unit);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBuyingPrice(request.getBuyingPrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setReorderLevel(request.getReorderLevel());
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deactivate(Long id) {
        Product product = getOrThrow(id);
        product.setActive(false);
        productRepository.save(product);
    }

    private Product getOrThrow(Long id) {
        return productRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }
}
