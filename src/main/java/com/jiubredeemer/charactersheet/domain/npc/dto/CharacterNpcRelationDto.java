package com.jiubredeemer.charactersheet.domain.npc.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CharacterNpcRelationDto {
    private UUID id;
    private UUID characterId;
    private UUID npcId;
    private String note;
    private RelationTypeEnum relationType;
}

