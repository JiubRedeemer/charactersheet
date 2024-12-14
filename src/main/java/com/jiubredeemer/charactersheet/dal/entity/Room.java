package com.jiubredeemer.charactersheet.dal.entity;

import com.jiubredeemer.charactersheet.domain.rooms.dto.RuleTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Data
public class Room {
    @Id
    private UUID roomId;
    private UUID ownerId;
    private RuleTypeEnum ruleType;
}
