package com.jiubredeemer.charactersheet.domain.pet.dto;

import lombok.Data;

@Data
public class PetAbilityRequest {
    private String abilityCode;
    private Long bonusValue;
}
