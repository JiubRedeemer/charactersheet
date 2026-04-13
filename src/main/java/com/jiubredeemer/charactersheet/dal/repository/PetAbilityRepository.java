package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.PetAbility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetAbilityRepository extends CrudRepository<PetAbility, UUID> {
    List<PetAbility> findByPetId(UUID petId);

    Optional<PetAbility> findByPetIdAndAbilityCode(UUID petId, String abilityCode);
}
