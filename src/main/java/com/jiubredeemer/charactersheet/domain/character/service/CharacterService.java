package com.jiubredeemer.charactersheet.domain.character.service;

import com.jiubredeemer.charactersheet.constants.LevelInfoEnum;
import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.dal.repository.CharacterRepository;
import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.ability.mapper.AbilityDtoMapper;
import com.jiubredeemer.charactersheet.domain.ability.service.AbilityService;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.service.CharacterBioService;
import com.jiubredeemer.charactersheet.domain.character.dto.AbilityShort;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.SkillShort;
import com.jiubredeemer.charactersheet.domain.character.mapper.CharacterDtoMapper;
import com.jiubredeemer.charactersheet.domain.clazz.service.ClazzIntegrationService;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.service.HealthService;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.service.LevelService;
import com.jiubredeemer.charactersheet.domain.race.service.RaceIntegrationService;
import com.jiubredeemer.charactersheet.domain.room.dto.RuleTypeEnum;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import com.jiubredeemer.charactersheet.domain.skill.mapper.SkillDtoMapper;
import com.jiubredeemer.charactersheet.domain.skill.service.SkillService;
import com.jiubredeemer.charactersheet.domain.util.HpCalculator;
import com.jiubredeemer.charactersheet.integration.RuleBookClient;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import com.jiubredeemer.charactersheet.integration.dto.race.RaceDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterDtoMapper characterDtoMapper;
    private final RaceIntegrationService raceIntegrationService;
    private final RuleBookClient ruleBookClient;
    private final LevelService levelService;
    private final HealthService healthService;
    private final ClazzIntegrationService clazzIntegrationService;
    private final AbilityDtoMapper abilityDtoMapper;
    private final AbilityService abilityService;
    private final HpCalculator hpCalculator;
    private final SkillDtoMapper skillDtoMapper;
    private final SkillService skillService;
    private final CharacterBioService characterBioService;

    @Transactional
    public CharacterDto saveCharacter(CreateCharacterDto createCharacterDto) {
        final RaceDto raceByCode = raceIntegrationService.getRaceByCode(createCharacterDto.getRaceCode(), createCharacterDto.getRoomId());
        final ClazzDto classByCode = clazzIntegrationService.getClassByCode(createCharacterDto.getClazzCode(), createCharacterDto.getRoomId());
        final RuleTypeEnum ruleType = ruleBookClient.getRoomRules(createCharacterDto.getRoomId()).getRuleType();

        final Character characterEntity = characterDtoMapper.toEntity(createCharacterDto, true);
        characterEntity.setId(UUID.randomUUID());

        if (ruleType.equals(RuleTypeEnum.DND5E)) {
            characterEntity.setProficiencyBonus(LevelInfoEnum.LVL_1.getProficiencyBonus());
            final long dexValue = getAbilityValueByCode(createCharacterDto.getAbilities(), "DEX");
            characterEntity.setArmoryClass(10 + (((int) dexValue - 10) / 2));
            characterEntity.setSpeed(raceByCode.getStats().getBaseSpeed());
            characterEntity.setInspiration(((int) dexValue - 10) / 2);
            Character savedEntity = characterRepository.save(characterEntity);
            //----------SAVED-----------
            CharacterDto characterDto = characterDtoMapper.toDto(savedEntity);
            characterDto.setAbilities(submitAbilities(characterDto, createCharacterDto.getAbilities()));
            characterDto.setSkills(submitSkills(characterDto, createCharacterDto.getSkills()));
            characterDto.setLevel(submitLevel(characterDto, LevelInfoEnum.LVL_1));
            characterDto.setHealth(submitHealth(characterDto, classByCode));
            characterDto.setCharacterBio(submitCharacterBio(characterDto, createCharacterDto));
            return characterDto;
        } else {
            throw new NotImplementedException("Not implemented yet");
        }
    }

    private CharacterBioDto submitCharacterBio(CharacterDto characterDto, CreateCharacterDto createCharacterDto) {
        final CharacterBioDto characterBioDto = new CharacterBioDto();
        characterBioDto.setCharacterId(characterDto.getId());
        characterBioDto.setAge(createCharacterDto.getAge());
        characterBioDto.setHeight(createCharacterDto.getHeight());
        characterBioDto.setWeight(createCharacterDto.getWeight());
        characterBioDto.setAttachments(createCharacterDto.getAttachments());
        characterBioDto.setHistory(createCharacterDto.getHistory());
        characterBioDto.setIdeals(createCharacterDto.getIdeals());
        characterBioDto.setPersonality(createCharacterDto.getPersonality());
        characterBioDto.setRelationships(createCharacterDto.getRelationships());
        characterBioDto.setWeaknesses(createCharacterDto.getWeaknesses());
        return characterBioService.saveCharacterBio(characterBioDto);
    }


    private LevelDto submitLevel(CharacterDto characterDto, LevelInfoEnum levelInfoEnum) {
        final LevelDto levelDto = new LevelDto();
        levelDto.setCharacterId(characterDto.getId());
        levelDto.setLevel(levelInfoEnum.getLevel());
        levelDto.setXp(levelInfoEnum.getXp());
        return levelService.saveLevel(levelDto);
    }

    private HealthDto submitHealth(CharacterDto characterDto, ClazzDto clazzDto) {
        final HealthDto healthDto = new HealthDto();
        healthDto.setCharacterId(characterDto.getId());
        healthDto.setMaxHp(hpCalculator.calculate(clazzDto.getStats().getHpDice(), characterDto.getId()));
        healthDto.setTempHp(0L);
        healthDto.setCurrentHp(healthDto.getMaxHp());
        return healthService.saveHealth(healthDto);
    }

    private List<AbilityDto> submitAbilities(CharacterDto characterDto, List<AbilityShort> abilities) {
        final List<AbilityDto> abilitiesForSave = abilities.stream().map(abilityShort -> {
            final AbilityDto ability = abilityDtoMapper.mapAbilityShortToDto(abilityShort);
            ability.setCharacterId(characterDto.getId());
            ability.setId(UUID.randomUUID());
            return ability;
        }).toList();
        return abilityService.saveAllAbilities(abilitiesForSave);
    }

    private List<SkillDto> submitSkills(CharacterDto characterDto, List<SkillShort> skills) {
        final List<SkillDto> skillsForSave = skills.stream().map(abilityShort -> {
            final SkillDto ability = skillDtoMapper.mapSkillShortToDto(abilityShort);
            ability.setCharacterId(characterDto.getId());
            ability.setId(UUID.randomUUID());
            return ability;
        }).toList();
        return skillService.saveAllSkills(skillsForSave);
    }

    private static long getAbilityValueByCode(List<AbilityShort> abilities, String code) {
        return abilities.stream().filter(abilityShort -> code.equals(abilityShort.getCode())).findAny().orElseThrow().getValue();
    }
}
