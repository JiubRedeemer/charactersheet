package com.jiubredeemer.charactersheet.domain.health.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class HealthDto {
    private UUID characterId;
    private Long currentHp;
    private Long maxHp;
    private Long tempHp;
}
