package com.jiubredeemer.charactersheet.domain.characterTraits.controller;

import com.jiubredeemer.charactersheet.domain.characterTraits.dto.CharacterTraitsDto;
import com.jiubredeemer.charactersheet.domain.characterTraits.service.CharacterTraitsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/characters/{characterId}/traits")
public class CharacterTraitsController {

    private final CharacterTraitsService characterTraitsService;

    public CharacterTraitsController(CharacterTraitsService characterTraitsService) {
        this.characterTraitsService = characterTraitsService;
    }

    @PutMapping()
    public ResponseEntity<Void> addTrait(@PathVariable UUID characterId,
                                         @RequestBody CharacterTraitsDto characterTraitsDto) {
        characterTraitsDto.setCharacterId(characterId);
        characterTraitsService.create(characterTraitsDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{traitId}")
    public ResponseEntity<Void> deleteTrait(@PathVariable UUID characterId,
                                         @PathVariable UUID traitId) {
        characterTraitsService.deleteById(characterId, traitId);
        return ResponseEntity.ok().build();
    }
}
