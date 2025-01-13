package com.jiubredeemer.charactersheet.integration.dto.race;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RaceStatsDto {
    private UUID id;
    private Integer maxAge;
    private Integer maxHeight;
    private Integer maxWeight;
    private Integer baseSpeed;
    private List<AbilityModifierDto> abilityModifiers;
    private List<RaceTraitDto> traits;
    private List<RaceProficiencyDto> proficiencies;
}
