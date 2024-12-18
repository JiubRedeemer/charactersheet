package com.jiubredeemer.charactersheet.domain.rooms.controller;

import com.jiubredeemer.charactersheet.domain.rooms.dto.RoomDto;
import com.jiubredeemer.charactersheet.domain.rooms.service.RoomsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@Tag(name = "Room Controller", description = "Управление комнатами в системе")
public class RoomController {

    private final RoomsService roomsService;

    @Operation(summary = "Создание или получение комнаты",
            description = "Создает новую комнату или возвращает существующую на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комната успешно создана или найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для создания комнаты"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при создании или получении комнаты")
    })
    @PutMapping()
    public RoomDto saveRoom(
            @Parameter(description = "Данные комнаты, которые необходимо сохранить или найти", required = true)
            @RequestBody RoomDto roomDto) {
        return roomsService.saveOrGetRoom(roomDto);
    }

    @DeleteMapping("{roomId}")
    public void deleteRoom(@Parameter(description = "ID комнаты для удаления", required = true)
                           @PathVariable("roomId") UUID roomId) {
        roomsService.deleteRoom(roomId);
    }
}
