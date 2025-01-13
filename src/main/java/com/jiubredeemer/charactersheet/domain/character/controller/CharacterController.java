package com.jiubredeemer.charactersheet.domain.character.controller;

import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterDto;
import com.jiubredeemer.charactersheet.domain.character.service.CharacterService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody CreateCharacterDto request) {
        return characterService.saveCharacter(request);
    }
}
