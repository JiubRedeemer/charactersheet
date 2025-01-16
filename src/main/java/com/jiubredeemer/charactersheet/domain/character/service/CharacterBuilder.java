package com.jiubredeemer.charactersheet.domain.character.service;

import com.jiubredeemer.charactersheet.constants.LevelInfoEnum;
import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.ability.service.AbilityService;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.ClassInfoDto;
import com.jiubredeemer.charactersheet.domain.character.dto.RaceInfoDto;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.service.CharacterBioService;
import com.jiubredeemer.charactersheet.domain.clazz.service.ClazzIntegrationService;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.service.HealthService;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.service.LevelService;
import com.jiubredeemer.charactersheet.domain.race.service.RaceIntegrationService;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import com.jiubredeemer.charactersheet.domain.skill.service.SkillService;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import com.jiubredeemer.charactersheet.integration.dto.race.RaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CharacterBuilder {

    private final AbilityService abilityService;
    private final SkillService skillService;
    private final LevelService levelService;
    private final HealthService healthService;
    private final CharacterBioService characterBioService;
    private final ClazzIntegrationService clazzIntegrationService;
    private final RaceIntegrationService raceIntegrationService;

    public CharacterDto enrichAbilities(CharacterDto character) {
        character.setAbilities(abilityService.findAllByCharacterId(character.getId()));
        return character;
    }

    public List<CharacterDto> enrichAbilities(List<CharacterDto> characters) {
        final Set<UUID> characterIds = characters.stream().map(CharacterDto::getId).collect(Collectors.toSet());
        final List<AbilityDto> abilities = abilityService.findAllByCharacterIds(characterIds);
        final Map<UUID, List<AbilityDto>> abilitiesByCharacterId = abilities.stream()
                .collect(Collectors.groupingBy(AbilityDto::getCharacterId));
        characters.forEach(character -> character.setAbilities(abilitiesByCharacterId.get(character.getId())));
        return characters;
    }

    public CharacterDto enrichSkills(CharacterDto character) {
        character.setSkills(skillService.findAllByCharacterId(character.getId()));
        return character;
    }

    public List<CharacterDto> enrichSkills(List<CharacterDto> characters) {
        final Set<UUID> characterIds = characters.stream().map(CharacterDto::getId).collect(Collectors.toSet());
        final List<SkillDto> skills = skillService.findAllByCharacterIds(characterIds);
        final Map<UUID, List<SkillDto>> skillsByCharacterId = skills.stream()
                .collect(Collectors.groupingBy(SkillDto::getCharacterId));
        characters.forEach(character -> character.setSkills(skillsByCharacterId.get(character.getId())));
        return characters;
    }

    public CharacterDto enrichLevel(CharacterDto character) {
        LevelDto levelDto = levelService.findByCharacterId(character.getId());
        levelDto.setNextLevelXp(levelDto.getLevel() < 20 ?
                LevelInfoEnum.valueOf("LVL_" + (levelDto.getLevel() + 1L)).getXp() :
                LevelInfoEnum.valueOf("LVL_" + levelDto.getLevel()).getXp());
        character.setLevel(levelDto);
        return character;
    }

    public List<CharacterDto> enrichLevel(List<CharacterDto> characters) {
        final Set<UUID> characterIds = characters.stream().map(CharacterDto::getId).collect(Collectors.toSet());
        final Map<UUID, LevelDto> characterIdLevelMap = levelService.findByCharacterIds(characterIds)
                .stream()
                .collect(Collectors.toMap(LevelDto::getCharacterId, Function.identity()));
        characters.forEach(character -> {
            LevelDto levelDto = characterIdLevelMap.get(character.getId());
            levelDto.setNextLevelXp(levelDto.getLevel() < 20 ?
                    LevelInfoEnum.valueOf("LVL_" + (levelDto.getLevel() + 1L)).getXp() :
                    LevelInfoEnum.valueOf("LVL_" + levelDto.getLevel()).getXp());
            character.setLevel(levelDto);
        });
        return characters;
    }

    public CharacterDto enrichHealth(CharacterDto character) {
        character.setHealth(healthService.findByCharacterId(character.getId()));
        return character;
    }

    public List<CharacterDto> enrichHealth(List<CharacterDto> characters) {
        final Set<UUID> characterIds = characters.stream().map(CharacterDto::getId).collect(Collectors.toSet());
        final Map<UUID, HealthDto> characterIdHealthMap = healthService.findByCharacterIds(characterIds)
                .stream()
                .collect(Collectors.toMap(HealthDto::getCharacterId, Function.identity()));
        characters.forEach(character -> character.setHealth(characterIdHealthMap.get(character.getId())));
        return characters;
    }

    public CharacterDto enrichCharacterBio(CharacterDto character) {
        character.setCharacterBio(characterBioService.findByCharacterId(character.getId()));
        return character;
    }

    public List<CharacterDto> enrichCharacterBio(List<CharacterDto> characters) {
        final Set<UUID> characterIds = characters.stream().map(CharacterDto::getId).collect(Collectors.toSet());
        final Map<UUID, CharacterBioDto> characterIdHealthMap = characterBioService.findByCharacterIds(characterIds)
                .stream()
                .collect(Collectors.toMap(CharacterBioDto::getCharacterId, Function.identity()));
        characters.forEach(character -> character.setCharacterBio(characterIdHealthMap.get(character.getId())));
        return characters;
    }

    public CharacterDto enrichClassInfo(CharacterDto character) {
        final ClazzDto classByCode = clazzIntegrationService.getClassByCode(character.getClazzCode(), character.getRoomId());
        final ClassInfoDto classInfoDto = new ClassInfoDto();
        classInfoDto.setCode(classByCode.getCode());
        classInfoDto.setName(classByCode.getName());
        character.setClazzInfo(classInfoDto);
        return character;
    }

    public CharacterDto enrichRaceInfo(CharacterDto character) {
        final RaceDto raceByCode = raceIntegrationService.getRaceByCode(character.getRaceCode(), character.getRoomId());
        final RaceInfoDto raceInfoDto = new RaceInfoDto();
        raceInfoDto.setCode(raceByCode.getCode());
        raceInfoDto.setName(raceByCode.getName());
        character.setRaceInfo(raceInfoDto);
        return character;
    }
}
