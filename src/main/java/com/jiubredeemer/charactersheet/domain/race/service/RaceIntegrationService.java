package com.jiubredeemer.charactersheet.domain.race.service;

import com.jiubredeemer.charactersheet.integration.RuleBookClient;
import com.jiubredeemer.charactersheet.integration.dto.race.RaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RaceIntegrationService {
    private final RuleBookClient ruleBookClient;

    public RaceDto getRaceByCode(String code, UUID roomId) {
        return ruleBookClient.getRaceByCode(code, roomId);
    }
}
