package com.jiubredeemer.charactersheet.domain.character.service;

import com.jiubredeemer.charactersheet.constants.LevelInfoEnum;
import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.dal.repository.CharacterRepository;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterRequest;
import com.jiubredeemer.charactersheet.domain.character.dto.FindCharacterByUserIdAndRoomIdRequest;
import com.jiubredeemer.charactersheet.domain.character.dto.RestTypeEnum;
import com.jiubredeemer.charactersheet.domain.character.mapper.CharacterDtoMapper;
import com.jiubredeemer.charactersheet.domain.clazz.service.ClazzIntegrationService;
import com.jiubredeemer.charactersheet.domain.race.service.RaceIntegrationService;
import com.jiubredeemer.charactersheet.domain.room.dto.RuleTypeEnum;
import com.jiubredeemer.charactersheet.domain.util.AbilityUtils;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import com.jiubredeemer.charactersheet.integration.RuleBookClient;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import com.jiubredeemer.charactersheet.integration.dto.race.RaceDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository repository;
    private final CharacterDtoMapper characterDtoMapper;
    private final RaceIntegrationService raceIntegrationService;
    private final ClazzIntegrationService clazzIntegrationService;
    private final RuleBookClient ruleBookClient;
    private final AbilityUtils abilityUtils;
    private final CharacterCommonService characterCommonService;
    private final CharacterBuilder characterBuilder;

    @Transactional
    public CharacterDto saveCharacter(CreateCharacterRequest createCharacterRequest) {
        final RaceDto raceByCode = raceIntegrationService.getRaceByCode(createCharacterRequest.getRaceCode(), createCharacterRequest.getRoomId());
        final ClazzDto classByCode = clazzIntegrationService.getClassByCode(createCharacterRequest.getClazzCode(), createCharacterRequest.getRoomId());
        final RuleTypeEnum ruleType = ruleBookClient.getRoomRules(createCharacterRequest.getRoomId()).getRuleType();

        final Character characterEntity = characterDtoMapper.toEntity(createCharacterRequest, true);
        characterEntity.setId(UUID.randomUUID());

        if (ruleType.equals(RuleTypeEnum.DND5E) || ruleType.equals(RuleTypeEnum.DND2024)) {
            characterEntity.setProficiencyBonus(LevelInfoEnum.LVL_1.getProficiencyBonus());
            final long dexValue = abilityUtils.getAbilityValueByCode(createCharacterRequest.getAbilities(), "DEX");
            characterEntity.setArmoryClass(10 + (((int) dexValue - 10) / 2));
            characterEntity.setBonusArmoryClass(0);
            characterEntity.setSpeed(raceByCode.getStats().getBaseSpeed());
            characterEntity.setBonusSpeed(0);
            characterEntity.setInspiration(0);
            characterEntity.setInitiative(((int) dexValue - 10) / 2);
            characterEntity.setBonusInitiative(0);
            characterEntity.setCurrentHpDiceCount(1);
            Character savedEntity = repository.save(characterEntity);
            //----------SAVED-----------
            CharacterDto characterDto = characterDtoMapper.toDto(savedEntity);
            characterDto.setAbilities(characterCommonService.createAbilities(characterDto, createCharacterRequest.getAbilities()));
            characterDto.setSkills(characterCommonService.createSkills(characterDto, createCharacterRequest.getSkills()));
            characterDto.setLevel(characterCommonService.createLevel(characterDto, LevelInfoEnum.LVL_1));
            characterDto.setHealth(characterCommonService.createHealth(characterDto, classByCode));
            characterDto.setCharacterBio(characterCommonService.createCharacterBio(characterDto, createCharacterRequest));
            return characterDto;
        } else {
            throw new NotImplementedException("Not implemented yet");
        }
    }


    public List<CharacterDto> findAllByRoomIdAndUserId(FindCharacterByUserIdAndRoomIdRequest request) {
        return Stream.of(characterDtoMapper
                        .toDto(repository.findByRoomIdAndUserId(request.getRoomId(), request.getUserId())))
                .map(characterBuilder::enrichLevel)
                .map(characterBuilder::enrichHealth)
                .findAny()
                .orElseThrow(() ->
                        new IllegalStateException("Characters was found, but was not built with roomId: %s and userId: %s"
                                .formatted(request.getRoomId(), request.getUserId())))
                .stream()
                .map(characterBuilder::enrichRaceInfo) //FIXME сделать нормально
                .map(characterBuilder::enrichClassInfo).toList();
    }

    public CharacterDto findById(UUID id) {
        return Stream.of(characterDtoMapper.toDto(repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Character not found by id " + id))))
                .map(characterBuilder::enrichAbilities)
                .map(characterBuilder::enrichSkills)
                .map(characterBuilder::enrichLevel)
                .map(characterBuilder::enrichHealth)
                .map(characterBuilder::enrichCharacterBio)
                .map(characterBuilder::enrichRaceInfo)
                .map(characterBuilder::enrichClassInfo)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Character was found, but was not built with id: " + id));
    }

    public CharacterDto getHeaderInfoByCharacterId(UUID id) {
        return Stream.of(characterDtoMapper.toDto(repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Character not found by id " + id))))
                .map(characterBuilder::enrichLevel)
                .map(characterBuilder::enrichRaceInfo)
                .map(characterBuilder::enrichClassInfo)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Character was found, but was not built with id: " + id));
    }

    public CharacterDto getSubheaderInfoByCharacterId(UUID id) {
        return Stream.of(characterDtoMapper.toDto(repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Character not found by id " + id))))
                .map(characterBuilder::enrichHealth)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Character was found, but was not built with id: " + id));
    }

    public CharacterDto getAbilitiesAndSkillsInfoByCharacterId(UUID id) {
        return Stream.of(characterDtoMapper.toDto(repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Character not found by id " + id))))
                .map(characterBuilder::enrichAbilities)
                .map(characterBuilder::enrichSkills)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Character was found, but was not built with id: " + id));
    }

    public CharacterDto getPersonalityByCharacterId(UUID id) {
        return Stream.of(characterDtoMapper.toDto(repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Character not found by id " + id))))
                .map(characterBuilder::enrichCharacterBio)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Character was found, but was not built with id: " + id));
    }

    public void updateArmoryClassBonusValue(UUID id, BonusValueUpdateRequest request) {
        repository.findById(id).ifPresentOrElse(character -> {
            character.setBonusArmoryClass(request.getBonusValue().intValue());
            repository.save(character);
        }, () -> {
            throw new NotFoundException("Character not found by id " + id);
        });
    }

    public void updateSpeedBonusValue(UUID id, BonusValueUpdateRequest request) {
        repository.findById(id).ifPresentOrElse(character -> {
            character.setBonusSpeed(request.getBonusValue().intValue());
            repository.save(character);
        }, () -> {
            throw new NotFoundException("Character not found by id " + id);
        });
    }

    public void updateInitiativeBonusValue(UUID id, BonusValueUpdateRequest request) {
        repository.findById(id).ifPresentOrElse(character -> {
            character.setBonusInitiative(request.getBonusValue().intValue());
            repository.save(character);
        }, () -> {
            throw new NotFoundException("Character not found by id " + id);
        });
    }

    public void characterRest(UUID characterId, RestTypeEnum restType, Integer hpDiceCount) {
        final CharacterDto characterDto = findById(characterId);
        final ClazzDto clazzDto = ruleBookClient.getClassByCode(characterDto.getClazzCode(), characterDto.getRoomId());
        characterCommonService.characterRest(characterId, restType, hpDiceCount, characterDto, clazzDto);
    }
}
