package com.jiubredeemer.charactersheet.domain.character.controller;

import com.jiubredeemer.charactersheet.domain.character.dto.*;
import com.jiubredeemer.charactersheet.domain.character.service.CharacterService;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public CharacterDto findById(
            @Parameter(description = "Найти персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.findById(id);
    }

    @GetMapping("/{id}/header")
    public CharacterDto getHeaderInfoByCharacterId(
            @Parameter(description = "Получить имя, расу, класс и уровень персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getHeaderInfoByCharacterId(id);
    }

    @GetMapping("/{id}/subheader")
    public CharacterDto getSubheaderInfoByCharacterId(
            @Parameter(description = "Получить КБ, скорость и хп персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getSubheaderInfoByCharacterId(id);
    }

    @GetMapping("/{id}/abilities")
    public CharacterDto getAbilitiesAndSkillsInfoByCharacterId(
            @Parameter(description = "Получить характеристики и навыки персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getAbilitiesAndSkillsInfoByCharacterId(id);
    }

    @PatchMapping("/{id}/armoryClass/bonus")
    public ResponseEntity<Void> updateArmoryClassBonusValue(
            @Parameter(description = "Запрос на изменение бонусного значения КЗ персонажа", required = true)
            @RequestBody BonusValueUpdateRequest request,
            @PathVariable UUID id) {
        characterService.updateArmoryClassBonusValue(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/speed/bonus")
    public ResponseEntity<Void> updateSpeedBonusValue(
            @Parameter(description = "Запрос на изменение бонусного значения скорости персонажа", required = true)
            @RequestBody BonusValueUpdateRequest request,
            @PathVariable UUID id) {
        characterService.updateSpeedBonusValue(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/initiative/bonus")
    public ResponseEntity<Void> updateInitiativeBonusValue(
            @Parameter(description = "Запрос на изменение бонусного значения инициативы персонажа", required = true)
            @RequestBody BonusValueUpdateRequest request,
            @PathVariable UUID id) {
        characterService.updateInitiativeBonusValue(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/rest/{restType}")
    public ResponseEntity<Void> characterRest(
            @PathVariable UUID id,
            @PathVariable RestTypeEnum restType,
            @RequestBody RestRequest restRequest
    ) {
        characterService.characterRest(id, restType, restRequest.getHpDiceCount());
        return ResponseEntity.ok().build();
    }
}
