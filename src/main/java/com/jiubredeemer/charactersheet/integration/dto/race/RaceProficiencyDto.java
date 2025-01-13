package com.jiubredeemer.charactersheet.integration.dto.race;

import com.jiubredeemer.charactersheet.constants.RaceProficiencyTypeEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class RaceProficiencyDto {
    private UUID id;
    private UUID raceStatsId;
    private RaceProficiencyTypeEnum type;
    private String code;
}
