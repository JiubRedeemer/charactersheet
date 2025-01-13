package com.jiubredeemer.charactersheet.domain.level.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LevelDto {
    private UUID characterId;
    private Long level;
    private Long xp;
}
