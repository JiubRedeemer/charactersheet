package com.jiubredeemer.charactersheet.domain.characters.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateCharacterDto {
    private UUID roomId;
    private String name;
    private String classCode;
    private String raceCode;
    private List<AbilityShort> abilities;
    private List<SkillShort> skills;
    private long age;
    private long height;
    private long weight;
    private String attachments;
    private String ideals;
    private String personality;
    private String relationships;
    private String weaknesses;
}

@Data
class AbilityShort {
    private String code;
    private long value;
}

@Data
class SkillShort {
    private String type;
}
