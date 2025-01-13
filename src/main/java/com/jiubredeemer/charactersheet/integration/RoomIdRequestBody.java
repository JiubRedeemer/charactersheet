package com.jiubredeemer.charactersheet.integration;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoomIdRequestBody {
    private UUID roomId;
}
