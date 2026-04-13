package com.jiubredeemer.charactersheet.domain.pet.service;

import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.dal.entity.Pet;
import com.jiubredeemer.charactersheet.dal.entity.PetAbility;
import com.jiubredeemer.charactersheet.dal.entity.PetSkill;
import com.jiubredeemer.charactersheet.dal.repository.CharacterRepository;
import com.jiubredeemer.charactersheet.dal.repository.PetAbilityRepository;
import com.jiubredeemer.charactersheet.dal.repository.PetRepository;
import com.jiubredeemer.charactersheet.dal.repository.PetSkillRepository;
import com.jiubredeemer.charactersheet.domain.pet.dto.*;
import com.jiubredeemer.charactersheet.domain.pet.mapper.PetMapper;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PetService {
    private static final List<String> DEFAULT_ABILITY_CODES = List.of("STR", "DEX", "CON", "INT", "WIS", "CHA");

    private final PetRepository petRepository;
    private final PetAbilityRepository petAbilityRepository;
    private final PetSkillRepository petSkillRepository;
    private final CharacterRepository characterRepository;
    private final PetMapper petMapper;

    @Transactional
    public PetDto createPet(UUID characterId, CreatePetRequest request) {
        validateName(request.getName());
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new NotFoundException("Character not found by id " + characterId));
        if (character.getDeletedAt() != null) {
            throw new NotFoundException("Character not found by id " + characterId);
        }

        final long maxHp = Math.max(0L, Objects.requireNonNullElse(request.getMaxHp(), 0L));
        final long initialCurrent = Objects.requireNonNullElse(request.getCurrentHp(), maxHp);
        final long currentHp = Math.max(0L, Math.min(initialCurrent, maxHp));
        final LocalDateTime now = LocalDateTime.now();

        final Pet pet = new Pet();
        pet.setId(UUID.randomUUID());
        pet.setCharacterId(characterId);
        pet.setName(request.getName().trim());
        pet.setAge(request.getAge());
        pet.setDescription(request.getDescription());
        pet.setAvatar(request.getAvatar());
        pet.setMaxHp(maxHp);
        pet.setCurrentHp(currentHp);
        pet.setArmorClass(request.getArmorClass());
        pet.setSpeed(request.getSpeed());
        pet.setSize(request.getSize());
        pet.setCreatureType(request.getCreatureType());
        pet.setProficiencyBonus(request.getProficiencyBonus());
        pet.setSenses(request.getSenses());
        pet.setLanguages(request.getLanguages());
        pet.setIsSummoned(Objects.requireNonNullElse(request.getIsSummoned(), false));
        pet.setIsActive(Objects.requireNonNullElse(request.getIsActive(), true));
        pet.setCreatedAt(now);
        pet.setUpdatedAt(now);
        pet.setNew(true);
        final Pet savedPet = petRepository.save(pet);

        final Map<String, Long> abilitiesFromRequest = new HashMap<>();
        if (request.getAbilities() != null) {
            request.getAbilities().forEach(ability ->
                    abilitiesFromRequest.put(ability.getAbilityCode(), Objects.requireNonNullElse(ability.getBonusValue(), 0L)));
        }

        final List<PetAbility> abilities = DEFAULT_ABILITY_CODES.stream().map(code -> {
            final PetAbility ability = new PetAbility();
            ability.setId(UUID.randomUUID());
            ability.setPetId(savedPet.getId());
            ability.setAbilityCode(code);
            ability.setBonusValue(abilitiesFromRequest.getOrDefault(code, 0L));
            ability.setNew(true);
            return ability;
        }).toList();
        petAbilityRepository.saveAll(abilities);

        return buildPetDto(savedPet);
    }

    public List<PetDto> findByCharacterId(UUID characterId) {
        validateCharacterExists(characterId);
        return petRepository.findByCharacterIdAndDeletedAtNull(characterId).stream().map(this::buildPetDto).toList();
    }

    public PetDto findById(UUID petId) {
        return buildPetDto(getPetOrThrow(petId));
    }

    @Transactional
    public void updateProfile(UUID petId, PetProfileUpdateRequest request) {
        final Pet pet = getPetOrThrow(petId);
        if (request.getName() != null) {
            validateName(request.getName());
            pet.setName(request.getName().trim());
        }
        if (request.getAge() != null) pet.setAge(request.getAge());
        if (request.getDescription() != null) pet.setDescription(request.getDescription());
        if (request.getAvatar() != null) pet.setAvatar(request.getAvatar());
        if (request.getArmorClass() != null) pet.setArmorClass(request.getArmorClass());
        if (request.getSpeed() != null) pet.setSpeed(request.getSpeed());
        if (request.getSize() != null) pet.setSize(request.getSize());
        if (request.getCreatureType() != null) pet.setCreatureType(request.getCreatureType());
        if (request.getProficiencyBonus() != null) pet.setProficiencyBonus(request.getProficiencyBonus());
        if (request.getSenses() != null) pet.setSenses(request.getSenses());
        if (request.getLanguages() != null) pet.setLanguages(request.getLanguages());
        if (request.getIsSummoned() != null) pet.setIsSummoned(request.getIsSummoned());
        if (request.getIsActive() != null) pet.setIsActive(request.getIsActive());
        pet.setUpdatedAt(LocalDateTime.now());
        petRepository.save(pet);
    }

    @Transactional
    public void updateAbilityBonus(UUID petId, String abilityCode, BonusValueUpdateRequest request) {
        final Pet pet = getPetOrThrow(petId);
        final PetAbility ability = petAbilityRepository.findByPetIdAndAbilityCode(pet.getId(), abilityCode)
                .orElseThrow(() -> new NotFoundException("Pet ability not found by code " + abilityCode));
        ability.setBonusValue(Objects.requireNonNullElse(request.getBonusValue(), 0L));
        petAbilityRepository.save(ability);
    }

    @Transactional
    public void updateCurrentHp(UUID petId, PetHealthCurrentUpdateRequest request) {
        final Pet pet = getPetOrThrow(petId);
        final long value = Math.max(0L, Objects.requireNonNullElse(request.getValue(), 0L));
        final PetHealthCurrentUpdateType type = Objects.requireNonNullElse(request.getType(), PetHealthCurrentUpdateType.ADD);
        switch (type) {
            case ADD -> pet.setCurrentHp(Math.min(pet.getCurrentHp() + value, pet.getMaxHp()));
            case SUBTRACT -> pet.setCurrentHp(Math.max(pet.getCurrentHp() - value, 0));
            case SET -> pet.setCurrentHp(Math.max(0L, Math.min(value, pet.getMaxHp())));
        }
        pet.setUpdatedAt(LocalDateTime.now());
        petRepository.save(pet);
    }

    @Transactional
    public void updateMaxHp(UUID petId, BonusValueUpdateRequest request) {
        final Pet pet = getPetOrThrow(petId);
        final long maxHp = Math.max(0L, Objects.requireNonNullElse(request.getBonusValue(), 0L));
        pet.setMaxHp(maxHp);
        pet.setCurrentHp(Math.min(pet.getCurrentHp(), maxHp));
        pet.setUpdatedAt(LocalDateTime.now());
        petRepository.save(pet);
    }

    @Transactional
    public PetSkillDto createSkill(UUID petId, PetSkillRequest request) {
        getPetOrThrow(petId);
        validateName(request.getName());
        final LocalDateTime now = LocalDateTime.now();
        final PetSkill skill = new PetSkill();
        skill.setId(UUID.randomUUID());
        skill.setPetId(petId);
        skill.setName(request.getName().trim());
        skill.setDescription(request.getDescription());
        skill.setCreatedAt(now);
        skill.setUpdatedAt(now);
        skill.setNew(true);
        return petMapper.toSkillDto(petSkillRepository.save(skill));
    }

    @Transactional
    public PetSkillDto updateSkill(UUID petId, UUID skillId, PetSkillRequest request) {
        getPetOrThrow(petId);
        final PetSkill skill = petSkillRepository.findByIdAndPetId(skillId, petId)
                .orElseThrow(() -> new NotFoundException("Pet skill not found by id " + skillId));
        if (request.getName() != null) {
            validateName(request.getName());
            skill.setName(request.getName().trim());
        }
        if (request.getDescription() != null) skill.setDescription(request.getDescription());
        skill.setUpdatedAt(LocalDateTime.now());
        return petMapper.toSkillDto(petSkillRepository.save(skill));
    }

    @Transactional
    public void deleteSkill(UUID petId, UUID skillId) {
        getPetOrThrow(petId);
        petSkillRepository.findByIdAndPetId(skillId, petId)
                .orElseThrow(() -> new NotFoundException("Pet skill not found by id " + skillId));
        petSkillRepository.deleteById(skillId);
    }

    @Transactional
    public void deleteLogical(UUID petId) {
        final Pet pet = getPetOrThrow(petId);
        pet.setDeletedAt(LocalDateTime.now());
        pet.setUpdatedAt(LocalDateTime.now());
        petRepository.save(pet);
    }

    private Pet getPetOrThrow(UUID petId) {
        return petRepository.findByIdAndDeletedAtNull(petId)
                .orElseThrow(() -> new NotFoundException("Pet not found by id " + petId));
    }

    private PetDto buildPetDto(Pet pet) {
        final List<PetAbility> abilities = petAbilityRepository.findByPetId(pet.getId());
        final List<PetSkill> skills = petSkillRepository.findByPetId(pet.getId());
        return petMapper.toDto(pet, abilities, skills);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    private void validateCharacterExists(UUID characterId) {
        final Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new NotFoundException("Character not found by id " + characterId));
        if (character.getDeletedAt() != null) {
            throw new NotFoundException("Character not found by id " + characterId);
        }
    }
}
