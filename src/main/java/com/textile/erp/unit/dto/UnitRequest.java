package com.textile.erp.unit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnitRequest {

    @NotBlank(message = "Unit name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Abbreviation is required")
    @Size(max = 20)
    private String abbreviation;
}
