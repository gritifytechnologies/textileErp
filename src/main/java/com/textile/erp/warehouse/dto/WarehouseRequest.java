package com.textile.erp.warehouse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WarehouseRequest {

    @NotBlank(message = "Warehouse name is required")
    @Size(max = 150)
    private String name;

    @NotBlank(message = "Warehouse code is required")
    @Size(max = 50)
    private String code;

    @Size(max = 500)
    private String address;

    @Size(max = 150)
    private String managerName;

    @Size(max = 30)
    private String phone;
}
