package com.jiubredeemer.charactersheet.integration.dto.background;

import lombok.Data;

import java.util.UUID;

@Data
public class BackgroundDto {
    private UUID id;
    private UUID roomId;
    private String name;
    private String description;
    private String code;
    private String imgUrl;
    private BackgroundStatsDto stats;
}
