package com.jiubredeemer.charactersheet.domain.characterSkill.controller;

import com.jiubredeemer.charactersheet.domain.characterSkill.dto.CharacterSkillsDto;
import com.jiubredeemer.charactersheet.domain.characterSkill.service.CharacterSkillsService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/characters/{characterId}/character-skills")
public class CharacterSkillsController {
    private final CharacterSkillsService characterSkillsService;

    public CharacterSkillsController(CharacterSkillsService characterSkillsService) {
        this.characterSkillsService = characterSkillsService;
    }

    @GetMapping("")
    public List<CharacterSkillsDto> getSkills(
            @Parameter(description = "Получить активные навыки персонажа по ID", required = true)
            @PathVariable UUID characterId) {
        return characterSkillsService.findByCharacterId(characterId);
    }

    @PutMapping("")
    public CharacterSkillsDto saveCharacterSkill(@RequestBody CharacterSkillsDto characterSkillsDto) {
        return characterSkillsService.saveCharacterSkill(characterSkillsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterSkill(@PathVariable("characterId") UUID characterId, @PathVariable("id") UUID id) {
        characterSkillsService.deleteCharacterSkill(characterId, id);
    }

    @PatchMapping("/{id}")
    public CharacterSkillsDto updateCharacterSkill(@PathVariable("id") UUID id, @RequestBody CharacterSkillsDto characterSkillsDto) {
        return characterSkillsService.updateCharacterSkill(id, characterSkillsDto);
    }

    @PatchMapping("/{id}/use")
    public CharacterSkillsDto useCharacterSkill(@PathVariable("characterId") UUID characterId, @PathVariable("id") UUID id) {
        return characterSkillsService.useCharacterSkill(characterId, id);
    }
}
