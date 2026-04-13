package com.jiubredeemer.charactersheet.domain.pet.dto;

import lombok.Data;

@Data
public class PetHealthCurrentUpdateRequest {
    private PetHealthCurrentUpdateType type;
    private Long value;
}
