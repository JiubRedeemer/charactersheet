package com.jiubredeemer.charactersheet.domain.character.dto;

import com.jiubredeemer.charactersheet.integration.dto.race.RaceTraitDto;
import lombok.Data;

import java.util.List;

@Data
public class RaceInfoDto {
    private String code;
    private String name;
    private String speciesCode;
    private String imgUrl;
    private List<RaceTraitDto> traits;
}
