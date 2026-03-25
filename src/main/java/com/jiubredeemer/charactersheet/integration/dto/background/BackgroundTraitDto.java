package com.jiubredeemer.charactersheet.integration.dto.background;

import lombok.Data;

import java.util.UUID;

@Data
public class BackgroundTraitDto {
    private UUID id;
    private UUID backgroundStatsId;
    private String name;
    private String code;
    private String description;
}
