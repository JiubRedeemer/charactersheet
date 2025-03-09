package com.jiubredeemer.charactersheet.domain.character.dto;

import lombok.Getter;

@Getter
public enum SectionEnum {
    AGE("age"), HEIGHT("height"),
    WEIGHT("weight"),
    ATTACHMENTS("attachments"),
    HISTORY("history"),
    IDEALS("ideals"),
    PERSONALITY("personality"), RELATIONSHIPS("relationships"),
    WEAKNESSES("weaknesses"),
    AVATAR("avatar");

    final private String fieldName;

    SectionEnum(String fieldName) {
        this.fieldName = fieldName;
    }
}
