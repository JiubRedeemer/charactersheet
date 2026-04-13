package com.jiubredeemer.charactersheet.domain.pet.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PetDto {
    private UUID id;
    private UUID characterId;
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
    private List<PetAbilityDto> abilities;
    private List<PetSkillDto> skills;
}
