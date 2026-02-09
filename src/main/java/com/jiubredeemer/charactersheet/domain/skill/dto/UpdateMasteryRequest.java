package com.jiubredeemer.charactersheet.domain.skill.dto;

import lombok.Data;

@Data
public class UpdateMasteryRequest {
    private Boolean isMastery;
    private Integer masteryValue;
    private Long bonusValue;
}
