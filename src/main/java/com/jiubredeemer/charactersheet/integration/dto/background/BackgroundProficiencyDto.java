package com.jiubredeemer.charactersheet.integration.dto.background;

import lombok.Data;

import java.util.UUID;

@Data
public class BackgroundProficiencyDto {
    private UUID id;
    private UUID backgroundStatsId;
    private BackgroundProficiencyTypeEnum type;
    private String code;
}
