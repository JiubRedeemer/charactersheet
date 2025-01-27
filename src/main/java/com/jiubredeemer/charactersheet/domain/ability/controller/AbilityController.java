package com.jiubredeemer.charactersheet.domain.ability.controller;

import com.jiubredeemer.charactersheet.domain.ability.service.AbilityService;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/characters/{characterId}/abilities")
public class AbilityController {

    private final AbilityService abilityService;

    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @PatchMapping("/{abilityCode}/bonus")
    public ResponseEntity<Void> updateBonusValueByCode(
            @Parameter(description = "Запрос на изменение бонусного значения характеристики персонажа", required = true)
            @PathVariable String abilityCode,
            @RequestBody BonusValueUpdateRequest request,
            @PathVariable UUID characterId) {
        abilityService.updateBonusValueByCode(characterId, abilityCode, request);
        return ResponseEntity.ok().build();
    }
}
