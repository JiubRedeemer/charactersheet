package com.jiubredeemer.charactersheet.domain.room.service;

import com.jiubredeemer.charactersheet.dal.repository.RoomRepository;
import com.jiubredeemer.charactersheet.domain.room.dto.RoomDto;
import com.jiubredeemer.charactersheet.domain.room.mapper.RoomDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomDtoMapper roomDtoMapper;

    public RoomDto saveOrGetRoom(RoomDto roomDto) {
        return roomRepository.findByRoomId(roomDto.getRoomId()).map(roomDtoMapper::toDto).orElseGet(() ->
                roomDtoMapper.toDto(roomRepository.save(roomDtoMapper.toEntity(roomDto, true))));
    }

    public RoomDto getById(UUID roomId) {
        return roomRepository.findByRoomId(roomId).map(roomDtoMapper::toDto).orElseThrow(() -> new NotFoundException("Room not found by id"));
    }

    public void deleteRoom(UUID roomId) {
        roomRepository.deleteById(roomId);
    }
}
