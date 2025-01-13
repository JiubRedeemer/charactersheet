package com.jiubredeemer.charactersheet.domain.ability.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AbilityDto {
    private UUID id;
    private UUID characterId;
    private String code;
    private Long value;
}
