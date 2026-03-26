package com.jiubredeemer.charactersheet.domain.background.service;

import com.jiubredeemer.charactersheet.integration.RuleBookClient;
import com.jiubredeemer.charactersheet.integration.dto.background.BackgroundDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BackgroundIntegrationService {
    private final RuleBookClient ruleBookClient;

    public BackgroundDto getBackGroundByCode(String code, UUID roomId) {
        if(code == null) return null;
        return ruleBookClient.getBackgroundByCode(code, roomId);
    }
}
