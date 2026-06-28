package com.textile.erp.unit.mapper;

import com.textile.erp.unit.dto.UnitDto;
import com.textile.erp.unit.dto.UnitRequest;
import com.textile.erp.unit.entity.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UnitMapper {

    UnitDto toDto(Unit unit);

    Unit toEntity(UnitRequest request);

    void updateEntity(UnitRequest request, @MappingTarget Unit unit);
}
