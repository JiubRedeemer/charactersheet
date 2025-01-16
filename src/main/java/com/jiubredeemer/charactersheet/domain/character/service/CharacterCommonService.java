package com.jiubredeemer.charactersheet.domain.character.service;

import com.jiubredeemer.charactersheet.constants.LevelInfoEnum;
import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.ability.mapper.AbilityDtoMapper;
import com.jiubredeemer.charactersheet.domain.ability.service.AbilityService;
import com.jiubredeemer.charactersheet.domain.character.dto.AbilityShort;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterRequest;
import com.jiubredeemer.charactersheet.domain.character.dto.SkillShort;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.service.CharacterBioService;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.service.HealthService;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.service.LevelService;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import com.jiubredeemer.charactersheet.domain.skill.mapper.SkillDtoMapper;
import com.jiubredeemer.charactersheet.domain.skill.service.SkillService;
import com.jiubredeemer.charactersheet.domain.util.HpCalculator;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CharacterCommonService {
    private final AbilityDtoMapper abilityDtoMapper;
    private final AbilityService abilityService;
    private final HpCalculator hpCalculator;
    private final SkillDtoMapper skillDtoMapper;
    private final SkillService skillService;
    private final CharacterBioService characterBioService;
    private final LevelService levelService;
    private final HealthService healthService;

    public CharacterBioDto createCharacterBio(CharacterDto characterDto, CreateCharacterRequest createCharacterRequest) {
        final CharacterBioDto characterBioDto = new CharacterBioDto();
        characterBioDto.setCharacterId(characterDto.getId());
        characterBioDto.setAge(createCharacterRequest.getAge());
        characterBioDto.setHeight(createCharacterRequest.getHeight());
        characterBioDto.setWeight(createCharacterRequest.getWeight());
        characterBioDto.setAttachments(createCharacterRequest.getAttachments());
        characterBioDto.setHistory(createCharacterRequest.getHistory());
        characterBioDto.setIdeals(createCharacterRequest.getIdeals());
        characterBioDto.setPersonality(createCharacterRequest.getPersonality());
        characterBioDto.setRelationships(createCharacterRequest.getRelationships());
        characterBioDto.setWeaknesses(createCharacterRequest.getWeaknesses());
        return characterBioService.saveCharacterBio(characterBioDto);
    }


    public LevelDto createLevel(CharacterDto characterDto, LevelInfoEnum levelInfoEnum) {
        final LevelDto levelDto = new LevelDto();
        levelDto.setCharacterId(characterDto.getId());
        levelDto.setLevel(levelInfoEnum.getLevel());
        levelDto.setXp(0L);
        return levelService.saveLevel(levelDto);
    }

    public HealthDto createHealth(CharacterDto characterDto, ClazzDto clazzDto) {
        final HealthDto healthDto = new HealthDto();
        healthDto.setCharacterId(characterDto.getId());
        healthDto.setMaxHp(hpCalculator.calculate(clazzDto.getStats().getHpDice(), characterDto.getId()));
        healthDto.setTempHp(0L);
        healthDto.setCurrentHp(healthDto.getMaxHp());
        return healthService.saveHealth(healthDto);
    }

    public List<AbilityDto> createAbilities(CharacterDto characterDto, List<AbilityShort> abilities) {
        final List<AbilityDto> abilitiesForSave = abilities.stream().map(abilityShort -> {
            final AbilityDto ability = abilityDtoMapper.mapAbilityShortToDto(abilityShort);
            ability.setCharacterId(characterDto.getId());
            ability.setId(UUID.randomUUID());
            return ability;
        }).toList();
        return abilityService.saveAllAbilities(abilitiesForSave);
    }

    public List<SkillDto> createSkills(CharacterDto characterDto, List<SkillShort> skills) {
        final List<SkillDto> skillsForSave = skills.stream().map(abilityShort -> {
            final SkillDto ability = skillDtoMapper.mapSkillShortToDto(abilityShort);
            ability.setCharacterId(characterDto.getId());
            ability.setId(UUID.randomUUID());
            return ability;
        }).toList();
        return skillService.saveAllSkills(skillsForSave);
    }

}
