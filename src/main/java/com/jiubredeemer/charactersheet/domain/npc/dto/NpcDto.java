package com.jiubredeemer.charactersheet.domain.npc.dto;

import com.jiubredeemer.charactersheet.domain.character.dto.ClassInfoDto;
import com.jiubredeemer.charactersheet.domain.character.dto.RaceInfoDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NpcDto {
    private UUID id;
    private UUID roomId;
    private String name;
    private String description;
    private NpcTypeEnum type;
    private String clazzCode;
    private ClassInfoDto clazzInfo;
    private String raceCode;
    private RaceInfoDto raceInfo;
    private String armoryClass;
    private String speed;
    private Integer initiative;
    private String imgUrl;
    private UUID createdBy;
    private LocalDateTime createdAt;
}

