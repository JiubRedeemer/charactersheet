package com.jiubredeemer.charactersheet.domain.npc.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SaveNpcRequest {
    private UUID id;
    private UUID roomId;
    private String name;
    private String description;
    private NpcTypeEnum type;
    private String clazzCode;
    private String raceCode;
    private String armoryClass;
    private String speed;
    private Integer initiative;
    private String imgUrl;
    private UUID createdBy;
}

