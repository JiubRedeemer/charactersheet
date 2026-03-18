package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Npc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NpcRepository extends CrudRepository<Npc, UUID> {

    List<Npc> findByRoomId(UUID roomId);
}

