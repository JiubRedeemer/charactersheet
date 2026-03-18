package com.jiubredeemer.charactersheet.domain.npc.controller;

import com.jiubredeemer.charactersheet.domain.npc.dto.CharacterNpcRelationDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.NpcDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.RelationTypeEnum;
import com.jiubredeemer.charactersheet.domain.npc.dto.SaveCharacterNpcRelationRequest;
import com.jiubredeemer.charactersheet.domain.npc.service.CharacterNpcRelationService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/character-npc-relations")
public class CharacterNpcRelationController {

    private final CharacterNpcRelationService relationService;

    public CharacterNpcRelationController(CharacterNpcRelationService relationService) {
        this.relationService = relationService;
    }

    @PutMapping
    public CharacterNpcRelationDto save(
            @Parameter(description = "Создание или обновление связи персонажа и NPC", required = true)
            @RequestBody SaveCharacterNpcRelationRequest request
    ) {
        return relationService.save(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Удалить связь персонажа и NPC по ID", required = true)
            @PathVariable UUID id
    ) {
        relationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/character/{characterId}")
    public List<NpcDto> findByCharacterId(
            @PathVariable UUID characterId
    ) {
        return relationService.findByCharacterId(characterId);
    }

    @GetMapping("/character/{characterId}/relationType/{relationType}")
    public List<NpcDto> getByRelationType(
            @PathVariable UUID characterId,
            @PathVariable RelationTypeEnum relationType
    ) {
        return relationService.getByRelationType(characterId, relationType);
    }
}

