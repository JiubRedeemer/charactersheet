package com.jiubredeemer.charactersheet.domain.character.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateCharacterRequest {
    private UUID roomId;
    private UUID userId;
    private String name;
    private String clazzCode;
    private String raceCode;
    private List<AbilityShort> abilities;
    private List<SkillShort> skills;
    private long age;
    private long height;
    private long weight;
    private String attachments;
    private String history;
    private String ideals;
    private String personality;
    private String relationships;
    private String weaknesses;
}

