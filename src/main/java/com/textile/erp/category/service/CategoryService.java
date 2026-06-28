package com.textile.erp.category.service;

import com.textile.erp.category.dto.CategoryDto;
import com.textile.erp.category.dto.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryRequest request);

    CategoryDto findById(Long id);

    Page<CategoryDto> findAll(Pageable pageable);

    List<CategoryDto> findRootCategories();

    List<CategoryDto> findChildren(Long parentId);

    CategoryDto update(Long id, CategoryRequest request);

    void deactivate(Long id);
}
