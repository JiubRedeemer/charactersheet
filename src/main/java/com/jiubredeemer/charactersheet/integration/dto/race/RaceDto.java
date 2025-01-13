package com.jiubredeemer.charactersheet.integration.dto.race;

import lombok.Data;

import java.util.UUID;

@Data
public class RaceDto {
    private UUID id;
    private UUID roomId;
    private String name;
    private String description;
    private String code;
    private RaceStatsDto stats;
}
