package com.jiubredeemer.charactersheet.domain.skill.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SkillDto {
    private UUID id;
    private UUID characterId;
    private String code;
    private Integer masteryValue;
    private Long bonusValue;
}
