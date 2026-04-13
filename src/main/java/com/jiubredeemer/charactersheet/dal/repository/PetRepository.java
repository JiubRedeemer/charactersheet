package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetRepository extends CrudRepository<Pet, UUID> {
    List<Pet> findByCharacterIdAndDeletedAtNull(UUID characterId);

    Optional<Pet> findByIdAndDeletedAtNull(UUID id);
}
