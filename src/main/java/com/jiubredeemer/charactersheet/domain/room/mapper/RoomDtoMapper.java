package com.jiubredeemer.charactersheet.domain.room.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Room;
import com.jiubredeemer.charactersheet.domain.room.dto.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper {
    public RoomDto toDto(Room room) {
        final RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(room.getRoomId());
        roomDto.setOwnerId(room.getOwnerId());
        roomDto.setRuleType(room.getRuleType());
        return roomDto;
    }

    public Room toEntity(RoomDto roomDto, boolean isNew) {
        final Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        room.setOwnerId(roomDto.getOwnerId());
        room.setRuleType(roomDto.getRuleType());
        room.setNew(isNew);
        return room;
    }
}
