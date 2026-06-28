package com.textile.erp.category.mapper;

import com.textile.erp.category.dto.CategoryDto;
import com.textile.erp.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "parentId",   source = "parent.id")
    @Mapping(target = "parentName", source = "parent.name")
    CategoryDto toDto(Category category);
}
