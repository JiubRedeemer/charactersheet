package com.jiubredeemer.charactersheet.domain.character.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FindCharacterByUserIdAndRoomIdRequest {
    private UUID roomId;
    private UUID userId;
    private List<RoomUserRole> roles;
}

