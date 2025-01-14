package com.jiubredeemer.charactersheet.domain.character.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FindCharacterByUserIdAndRoomIdRequest {
    private UUID roomId;
    private UUID userId;
}

