package com.jiubredeemer.charactersheet.domain.pet.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreatePetRequest {
    private String name;
    private Long age;
    private String description;
    private String avatar;
    private Long maxHp;
    private Long currentHp;
    private Integer armorClass;
    private Integer speed;
    private String size;
    private String creatureType;
    private Integer proficiencyBonus;
    private String senses;
    private String languages;
    private Boolean isSummoned;
    private Boolean isActive;
    private List<PetAbilityRequest> abilities;
}
