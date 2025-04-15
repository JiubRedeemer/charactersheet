package com.jiubredeemer.charactersheet.domain.util.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class BonusValueUpdateRequest {
    private Long bonusValue;

    public void setBonusValue(Long bonusValue) {
        this.bonusValue = Objects.requireNonNullElse(bonusValue, 0L);
    }
}
