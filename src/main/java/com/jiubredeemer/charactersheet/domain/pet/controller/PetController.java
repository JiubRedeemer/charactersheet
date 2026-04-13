package com.jiubredeemer.charactersheet.domain.pet.controller;

import com.jiubredeemer.charactersheet.domain.pet.dto.*;
import com.jiubredeemer.charactersheet.domain.pet.service.PetService;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/api/characters/{characterId}/pets")
    public PetDto createPet(@PathVariable UUID characterId, @RequestBody CreatePetRequest request) {
        return petService.createPet(characterId, request);
    }

    @GetMapping("/api/characters/{characterId}/pets")
    public List<PetDto> getPetsByCharacterId(@PathVariable UUID characterId) {
        return petService.findByCharacterId(characterId);
    }

    @GetMapping("/api/pets/{petId}")
    public PetDto getPetById(@PathVariable UUID petId) {
        return petService.findById(petId);
    }

    @PatchMapping("/api/pets/{petId}/profile")
    public ResponseEntity<Void> updateProfile(@PathVariable UUID petId, @RequestBody PetProfileUpdateRequest request) {
        petService.updateProfile(petId, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/pets/{petId}/abilities/{abilityCode}/bonus")
    public ResponseEntity<Void> updateAbilityBonus(
            @PathVariable UUID petId,
            @PathVariable String abilityCode,
            @RequestBody BonusValueUpdateRequest request
    ) {
        petService.updateAbilityBonus(petId, abilityCode, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/pets/{petId}/health/current")
    public ResponseEntity<Void> updateCurrentHp(
            @PathVariable UUID petId,
            @RequestBody PetHealthCurrentUpdateRequest request
    ) {
        petService.updateCurrentHp(petId, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/pets/{petId}/health/max")
    public ResponseEntity<Void> updateMaxHp(
            @PathVariable UUID petId,
            @RequestBody BonusValueUpdateRequest request
    ) {
        petService.updateMaxHp(petId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/pets/{petId}/skills")
    public PetSkillDto createSkill(@PathVariable UUID petId, @RequestBody PetSkillRequest request) {
        return petService.createSkill(petId, request);
    }

    @PatchMapping("/api/pets/{petId}/skills/{skillId}")
    public PetSkillDto updateSkill(
            @PathVariable UUID petId,
            @PathVariable UUID skillId,
            @RequestBody PetSkillRequest request
    ) {
        return petService.updateSkill(petId, skillId, request);
    }

    @DeleteMapping("/api/pets/{petId}/skills/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID petId, @PathVariable UUID skillId) {
        petService.deleteSkill(petId, skillId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/pets/{petId}")
    public ResponseEntity<Void> deletePetLogical(@PathVariable UUID petId) {
        petService.deleteLogical(petId);
        return ResponseEntity.ok().build();
    }
}
