package com.jiubredeemer.charactersheet.domain.health.dto;

import lombok.Data;

@Data
public class UpdateCurrentHealthRequest {
    private UpdateHealthTypeEnum type;
    private Long value;
}
