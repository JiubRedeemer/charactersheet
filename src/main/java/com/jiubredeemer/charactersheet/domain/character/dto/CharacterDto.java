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
    private ClassInfoDto clazzInfo;
    private String raceCode;
    private RaceInfoDto raceInfo;
    private Integer proficiencyBonus;
    private Integer armoryClass;
    private Integer bonusArmoryClass;
    private Integer speed;
    private Integer bonusSpeed;
    private Integer inspiration;
    private Integer initiative;
    private Integer bonusInitiative;
    private Integer currentHpDiceCount;
    private List<AbilityDto> abilities;
    private List<SkillDto> skills;
    private LevelDto level;
    private HealthDto health;
    private CharacterBioDto characterBio;
    private Boolean isOwner;

}
