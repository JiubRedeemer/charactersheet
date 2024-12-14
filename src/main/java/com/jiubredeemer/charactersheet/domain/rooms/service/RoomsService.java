package com.jiubredeemer.charactersheet.domain.rooms.service;

import com.jiubredeemer.charactersheet.dal.repository.RoomRepository;
import com.jiubredeemer.charactersheet.domain.rooms.dto.RoomDto;
import com.jiubredeemer.charactersheet.domain.rooms.mapper.RoomDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomsService {
    private final RoomRepository roomRepository;
    private final RoomDtoMapper roomDtoMapper;

    public RoomDto saveOrGetRoom(RoomDto roomDto) {
        return roomRepository.findByRoomId(roomDto.getRoomId()).map(roomDtoMapper::toRoomDto).orElseGet(() ->
                roomDtoMapper.toRoomDto(roomRepository.save(roomDtoMapper.toRoomEntity(roomDto))));
    }

    public RoomDto getById(UUID roomId) {
        return roomRepository.findByRoomId(roomId).map(roomDtoMapper::toRoomDto).orElseThrow(() -> new NotFoundException("Room not found by id"));
    }
}
