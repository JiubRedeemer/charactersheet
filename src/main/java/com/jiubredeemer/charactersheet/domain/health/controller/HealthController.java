package com.jiubredeemer.charactersheet.domain.health.controller;

import com.jiubredeemer.charactersheet.domain.health.service.HealthService;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/characters/{characterId}/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @PatchMapping("/bonus")
    public ResponseEntity<Void> updateBonusValueByCode(
            @Parameter(description = "Запрос на изменение бонусного значения здоровья персонажа", required = true)
            @RequestBody BonusValueUpdateRequest request,
            @PathVariable UUID characterId) {
        healthService.updateBonusValue(characterId, request);
        return ResponseEntity.ok().build();
    }
}
