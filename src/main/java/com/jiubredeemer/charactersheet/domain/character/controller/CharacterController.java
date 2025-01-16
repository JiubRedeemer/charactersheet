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

    @PostMapping()
    public List<CharacterDto> findAllByRoomIdAndUserId(
            @Parameter(description = "Найти персонажей по ID комнаты и пользователя", required = true)
            @RequestBody FindCharacterByUserIdAndRoomIdRequest request) {
        return characterService.findAllByRoomIdAndUserId(request);
    }

    @PostMapping("/{id}")
    public CharacterDto findById(
            @Parameter(description = "Найти персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.findById(id);
    }

    @PostMapping("/{id}/header")
    public CharacterDto getHeaderInfoByCharacterId(
            @Parameter(description = "Получить имя, расу, класс и уровень персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getHeaderInfoByCharacterId(id);
    }

    @PostMapping("/{id}/subheader")
    public CharacterDto getSubheaderInfoByCharacterId(
            @Parameter(description = "Получить КБ, скорость и хп персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getSubheaderInfoByCharacterId(id);
    }

    @PostMapping("/{id}/abilities")
    public CharacterDto getAbilitiesAndSkillsInfoByCharacterId(
            @Parameter(description = "Получить характеристики и навыки персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getAbilitiesAndSkillsInfoByCharacterId(id);
    }
}
