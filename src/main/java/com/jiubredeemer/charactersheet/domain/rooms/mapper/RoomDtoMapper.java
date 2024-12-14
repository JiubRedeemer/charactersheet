package com.jiubredeemer.charactersheet.domain.rooms.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Room;
import com.jiubredeemer.charactersheet.domain.rooms.dto.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper {
    public RoomDto toRoomDto(Room room) {
        final RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(room.getRoomId());
        roomDto.setOwnerId(room.getOwnerId());
        roomDto.setRuleType(room.getRuleType());
        return roomDto;
    }

    public Room toRoomEntity(RoomDto roomDto) {
        final Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        room.setOwnerId(roomDto.getOwnerId());
        room.setRuleType(roomDto.getRuleType());
        return room;
    }
}
