package com.jiubredeemer.charactersheet.domain.character.dto;

import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CharacterDto {
    private UUID id;
    private UUID roomId;
    private UUID userId;
    private String name;
    private String clazzCode;
    private String raceCode;
    private Integer proficiencyBonus;
    private Integer armoryClass;
    private Integer speed;
    private Integer inspiration;
    private List<AbilityDto> abilities;
    private List<SkillDto> skills;
    private LevelDto level;
    private HealthDto health;
    private CharacterBioDto characterBio;
}
