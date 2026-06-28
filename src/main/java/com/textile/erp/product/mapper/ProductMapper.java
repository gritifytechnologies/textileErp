package com.textile.erp.product.mapper;

import com.textile.erp.product.dto.ProductDto;
import com.textile.erp.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(target = "categoryId",       source = "category.id")
    @Mapping(target = "categoryName",     source = "category.name")
    @Mapping(target = "unitId",           source = "unit.id")
    @Mapping(target = "unitName",         source = "unit.name")
    @Mapping(target = "unitAbbreviation", source = "unit.abbreviation")
    ProductDto toDto(Product product);
}
