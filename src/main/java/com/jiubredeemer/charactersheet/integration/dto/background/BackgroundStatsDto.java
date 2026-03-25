package com.jiubredeemer.charactersheet.integration.dto.background;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BackgroundStatsDto {
    private UUID id;
    /** Three ability codes to choose from: assign +2 to one and +1 to another, or +1 to each. */
    private List<String> abilityModifiers;
    private List<BackgroundTraitDto> traits;
    private List<BackgroundProficiencyDto> proficiencies;
}
