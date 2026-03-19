package com.jiubredeemer.charactersheet.domain.npc.controller;

import com.jiubredeemer.charactersheet.domain.npc.dto.NpcDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.SaveNpcRequest;
import com.jiubredeemer.charactersheet.domain.npc.service.NpcService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/npcs")
public class NpcController {

    private final NpcService npcService;

    public NpcController(NpcService npcService) {
        this.npcService = npcService;
    }

    @PutMapping
    public NpcDto save(
            @Parameter(description = "Создание или обновление NPC", required = true)
            @RequestBody SaveNpcRequest request
    ) {
        return npcService.save(request);
    }

    @GetMapping("/{id}")
    public NpcDto findById(
            @Parameter(description = "Найти NPC по ID", required = true)
            @PathVariable UUID id
    ) {
        return npcService.findById(id);
    }

    @GetMapping("/room/{roomId}")
    public List<NpcDto> findByRoomId(
            @Parameter(description = "Найти NPC по ID комнаты", required = true)
            @PathVariable UUID roomId,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String characterId,
            @RequestParam(required = false) Boolean forceAll
    ) {
        if (characterId == null || characterId.isEmpty() || characterId.equals("null") || characterId.equals("undefined")) {
            return npcService.findByRoomId(roomId, userId, null, forceAll);
        } else {
            return npcService.findByRoomId(roomId, userId, UUID.fromString(characterId), forceAll);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Удалить NPC по ID", required = true)
            @PathVariable UUID id
    ) {
        npcService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

