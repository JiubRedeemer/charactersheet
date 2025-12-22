package com.jiubredeemer.charactersheet.domain.characterSkill.dto;

import com.jiubredeemer.charactersheet.dal.entity.ChargesRefillEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class CharacterSkillsDto {
    private UUID id;
    private UUID characterId;
    private String name;
    private String description;
    private String shortDescription;
    private Integer charges;
    private Integer currentCharges;
    private String castTime;
    private String distance;
    private ChargesRefillEnum chargesRefill;
    private String imgUrl;
}
