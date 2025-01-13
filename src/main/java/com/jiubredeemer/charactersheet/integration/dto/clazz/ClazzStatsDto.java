package com.jiubredeemer.charactersheet.integration.dto.clazz;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ClazzStatsDto {
    private UUID id;
    private String hpDice;
    private List<AbilityShortDto> savingThrowsAbilities;
    private List<AvailableSkillDto> availableSkills;
}
