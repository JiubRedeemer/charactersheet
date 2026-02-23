package com.jiubredeemer.charactersheet.domain.skill.controller;

import com.jiubredeemer.charactersheet.domain.skill.dto.UpdateMasteryRequest;
import com.jiubredeemer.charactersheet.domain.skill.service.SkillService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/characters/{characterId}/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PatchMapping("{code}/updateMastery")
    public ResponseEntity<Void> updateMasteryByCode(
            @Parameter(description = "Запрос на изменение бонусного значения навыка персонажа", required = true)
            @RequestBody UpdateMasteryRequest request,
            @Parameter(description = "Код навыка, или SAVING_THROW_ABILITYCODE или CHECK_ABILITYCODE")
            @PathVariable String code,
            @PathVariable UUID characterId) {
        skillService.updateMastery(characterId, code, request);
        return ResponseEntity.ok().build();
    }
}
