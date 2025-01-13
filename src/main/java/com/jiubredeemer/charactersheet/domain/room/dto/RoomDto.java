package com.jiubredeemer.charactersheet.domain.room.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomDto {
    private UUID roomId;
    private UUID ownerId;
    private RuleTypeEnum ruleType;
}
