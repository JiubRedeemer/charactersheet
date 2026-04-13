package com.jiubredeemer.charactersheet.domain.pet.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Pet;
import com.jiubredeemer.charactersheet.dal.entity.PetAbility;
import com.jiubredeemer.charactersheet.dal.entity.PetSkill;
import com.jiubredeemer.charactersheet.domain.pet.dto.PetAbilityDto;
import com.jiubredeemer.charactersheet.domain.pet.dto.PetDto;
import com.jiubredeemer.charactersheet.domain.pet.dto.PetSkillDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetMapper {
    public PetDto toDto(Pet pet, List<PetAbility> abilities, List<PetSkill> skills) {
        final PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setCharacterId(pet.getCharacterId());
        dto.setName(pet.getName());
        dto.setAge(pet.getAge());
        dto.setDescription(pet.getDescription());
        dto.setAvatar(pet.getAvatar());
        dto.setMaxHp(pet.getMaxHp());
        dto.setCurrentHp(pet.getCurrentHp());
        dto.setArmorClass(pet.getArmorClass());
        dto.setSpeed(pet.getSpeed());
        dto.setSize(pet.getSize());
        dto.setCreatureType(pet.getCreatureType());
        dto.setProficiencyBonus(pet.getProficiencyBonus());
        dto.setSenses(pet.getSenses());
        dto.setLanguages(pet.getLanguages());
        dto.setIsSummoned(pet.getIsSummoned());
        dto.setIsActive(pet.getIsActive());
        dto.setAbilities(toAbilityDto(abilities));
        dto.setSkills(toSkillDto(skills));
        return dto;
    }

    public List<PetAbilityDto> toAbilityDto(List<PetAbility> abilities) {
        return abilities.stream().map(this::toAbilityDto).toList();
    }

    public PetAbilityDto toAbilityDto(PetAbility ability) {
        final PetAbilityDto dto = new PetAbilityDto();
        dto.setId(ability.getId());
        dto.setPetId(ability.getPetId());
        dto.setAbilityCode(ability.getAbilityCode());
        dto.setBonusValue(ability.getBonusValue());
        return dto;
    }

    public List<PetSkillDto> toSkillDto(List<PetSkill> skills) {
        return skills.stream().map(this::toSkillDto).toList();
    }

    public PetSkillDto toSkillDto(PetSkill skill) {
        final PetSkillDto dto = new PetSkillDto();
        dto.setId(skill.getId());
        dto.setPetId(skill.getPetId());
        dto.setName(skill.getName());
        dto.setDescription(skill.getDescription());
        return dto;
    }
}
