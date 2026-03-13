package com.jiubredeemer.charactersheet.domain.characterTraits.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CharacterTraitsDto {
    private UUID id;
    private UUID characterId;
    private String name;
    private String description;
}
