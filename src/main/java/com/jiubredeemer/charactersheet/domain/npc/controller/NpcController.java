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
            @PathVariable UUID roomId
    ) {
        return npcService.findByRoomId(roomId);
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

