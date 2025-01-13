package com.jiubredeemer.charactersheet.integration.dto.race;

import lombok.Data;

import java.util.UUID;

@Data
public class RaceTraitDto {
    private UUID id;
    private UUID raceStatsId;
    private String name;
    private String code;
    private String description;
}
