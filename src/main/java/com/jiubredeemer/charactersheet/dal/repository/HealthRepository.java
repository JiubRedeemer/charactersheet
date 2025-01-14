package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Health;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface HealthRepository extends CrudRepository<Health, UUID> {
    List<Health> findByCharacterIdIn(Collection<UUID> characterIds);
}
