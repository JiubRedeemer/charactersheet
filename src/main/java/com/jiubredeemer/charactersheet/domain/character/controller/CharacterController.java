package com.jiubredeemer.charactersheet.domain.character.controller;

import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterRequest;
import com.jiubredeemer.charactersheet.domain.character.dto.FindCharacterByUserIdAndRoomIdRequest;
import com.jiubredeemer.charactersheet.domain.character.service.CharacterService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PutMapping()
    public CharacterDto saveCharacter(
            @Parameter(description = "Запрос на создание персонажа", required = true)
            @RequestBody CreateCharacterRequest request) {
        return characterService.saveCharacter(request);
    }

    @GetMapping()
    public List<CharacterDto> findAllByRoomIdAndUserId(
            @Parameter(description = "Найти персонажей по ID комнаты и пользователя", required = true)
            @RequestBody FindCharacterByUserIdAndRoomIdRequest request) {
        return characterService.findAllByRoomIdAndUserId(request);
    }

    @GetMapping("/{id}")
    public CharacterDto findById(
            @Parameter(description = "Найти персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.findById(id);
    }
}
