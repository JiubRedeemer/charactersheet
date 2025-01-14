package com.jiubredeemer.charactersheet.domain.character.service;

import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.ability.service.AbilityService;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.service.CharacterBioService;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.service.HealthService;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.service.LevelService;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import com.jiubredeemer.charactersheet.domain.skill.service.SkillService;
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
        character.setLevel(levelService.findByCharacterId(character.getId()));
        return character;
    }

    public List<CharacterDto> enrichLevel(List<CharacterDto> characters) {
        final Set<UUID> characterIds = characters.stream().map(CharacterDto::getId).collect(Collectors.toSet());
        final Map<UUID, LevelDto> characterIdLevelMap = levelService.findByCharacterIds(characterIds)
                .stream()
                .collect(Collectors.toMap(LevelDto::getCharacterId, Function.identity()));
        characters.forEach(character -> character.setLevel(characterIdLevelMap.get(character.getId())));
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
}
