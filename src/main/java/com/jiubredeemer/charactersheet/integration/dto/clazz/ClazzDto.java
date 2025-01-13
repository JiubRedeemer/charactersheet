package com.jiubredeemer.charactersheet.integration.dto.clazz;

import lombok.Data;

import java.util.UUID;

@Data
public class ClazzDto {
    private UUID id;
    private UUID roomId;
    private String name;
    private String description;
    private String code;
    private ClazzStatsDto stats;
}
