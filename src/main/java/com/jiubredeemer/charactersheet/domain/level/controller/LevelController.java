package com.jiubredeemer.charactersheet.domain.level.controller;

import com.jiubredeemer.charactersheet.domain.level.dto.UpdateCurrentXpRequest;
import com.jiubredeemer.charactersheet.domain.level.service.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/characters/{characterId}/level")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PatchMapping("/updateCurrent")
    public ResponseEntity<Void> updateCurrentXpById(
            @RequestBody UpdateCurrentXpRequest request,
            @PathVariable UUID characterId) {
        levelService.updateCurrentXp(characterId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/up")
    public ResponseEntity<Void> levelUp(@PathVariable UUID characterId,
                                        @RequestParam(defaultValue = "false") Boolean force) {
        levelService.levelUp(characterId, force);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/down")
    public ResponseEntity<Void> levelDown(@PathVariable UUID characterId) {
        levelService.levelDown(characterId);
        return ResponseEntity.ok().build();
    }
}
