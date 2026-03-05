package com.jiubredeemer.charactersheet.domain.room.service;

import com.jiubredeemer.charactersheet.dal.entity.Room;
import com.jiubredeemer.charactersheet.dal.repository.RoomRepository;
import com.jiubredeemer.charactersheet.domain.room.dto.RoomDto;
import com.jiubredeemer.charactersheet.domain.room.mapper.RoomDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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

    public void deleteRoomLogical(UUID roomId) {
        Optional<Room> byRoomId = roomRepository.findByRoomId(roomId);
        if (byRoomId.isPresent()) {
            byRoomId.get().setDeletedAt(LocalDateTime.now());
            roomRepository.save(byRoomId.get());
        }
    }
}
