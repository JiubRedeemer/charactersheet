package com.jiubredeemer.charactersheet.domain.pet.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PetAbilityDto {
    private UUID id;
    private UUID petId;
    private String abilityCode;
    private Long bonusValue;
}
