package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.PetSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetSkillRepository extends CrudRepository<PetSkill, UUID> {
    List<PetSkill> findByPetId(UUID petId);

    Optional<PetSkill> findByIdAndPetId(UUID id, UUID petId);
}
