package com.jiubredeemer.charactersheet.domain.characterBio.controller;

import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.service.CharacterService;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioUpdateRequest;
import com.jiubredeemer.charactersheet.domain.characterBio.service.CharacterBioService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/characters")
public class CharacterBioController {
    private final CharacterService characterService;
    private final CharacterBioService characterBioService;

    public CharacterBioController(CharacterService characterService,
                                  CharacterBioService characterBioService) {
        this.characterService = characterService;
        this.characterBioService = characterBioService;
    }

    @GetMapping("/{id}/bio")
    public CharacterDto getBio(
            @Parameter(description = "Получить характеристики и навыки персонажа по ID", required = true)
            @PathVariable UUID id) {
        return characterService.getPersonalityByCharacterId(id);
    }

    @PatchMapping("/{id}/bio/{section}")
    public CharacterDto updateBio(
            @Parameter(description = "Получить характеристики и навыки персонажа по ID", required = true)
            @PathVariable UUID id,
            @PathVariable String section,
            @RequestBody CharacterBioUpdateRequest characterBioUpdateRequest) {
        characterBioService.updateBio(id, section, characterBioUpdateRequest);
        return characterService.getPersonalityByCharacterId(id);
    }
}
