package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Ability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AbilityRepository extends CrudRepository<Ability, UUID> {
    Optional<Ability> findByCharacterIdAndCode(UUID characterId, String code);

}
