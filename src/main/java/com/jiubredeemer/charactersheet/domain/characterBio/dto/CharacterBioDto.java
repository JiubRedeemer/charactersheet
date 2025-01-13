package com.jiubredeemer.charactersheet.domain.characterBio.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CharacterBioDto {
    private UUID characterId;
    private Long age;
    private Long height;
    private Long weight;
    private String attachments;
    private String history;
    private String ideals;
    private String personality;
    private String relationships;
    private String weaknesses;
}
