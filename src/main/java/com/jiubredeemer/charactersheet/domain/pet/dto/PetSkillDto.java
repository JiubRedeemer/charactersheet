package com.jiubredeemer.charactersheet.domain.pet.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PetSkillDto {
    private UUID id;
    private UUID petId;
    private String name;
    private String description;
}
