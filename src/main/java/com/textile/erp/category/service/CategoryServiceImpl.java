package com.textile.erp.category.service;

import com.textile.erp.category.dto.CategoryDto;
import com.textile.erp.category.dto.CategoryRequest;
import com.textile.erp.category.entity.Category;
import com.textile.erp.category.mapper.CategoryMapper;
import com.textile.erp.category.repository.CategoryRepository;
import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.security.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper     categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper     = categoryMapper;
    }

    @Override
    public CategoryDto create(CategoryRequest request) {
        Long tenantId = TenantContext.getTenantId();
        if (categoryRepository.existsByNameAndTenantId(request.getName(), tenantId)) {
            throw new DuplicateResourceException("Category", "name", request.getName());
        }
        Category parent = null;
        if (request.getParentId() != null) {
            parent = getOrThrow(request.getParentId());
        }
        Category category = Category.builder()
                .tenantId(tenantId)
                .name(request.getName())
                .description(request.getDescription())
                .parent(parent)
                .active(true)
                .build();
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return categoryMapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(categoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findRootCategories() {
        return categoryRepository.findAllByTenantIdAndParentIsNull(TenantContext.getTenantId())
                .stream().map(categoryMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findChildren(Long parentId) {
        return categoryRepository.findAllByTenantIdAndParentId(TenantContext.getTenantId(), parentId)
                .stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto update(Long id, CategoryRequest request) {
        Category category = getOrThrow(id);
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        if (request.getParentId() != null) {
            category.setParent(getOrThrow(request.getParentId()));
        } else {
            category.setParent(null);
        }
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deactivate(Long id) {
        Category category = getOrThrow(id);
        category.setActive(false);
        categoryRepository.save(category);
    }

    private Category getOrThrow(Long id) {
        return categoryRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }
}
