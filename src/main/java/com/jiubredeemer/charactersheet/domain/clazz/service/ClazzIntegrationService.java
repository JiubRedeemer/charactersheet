package com.jiubredeemer.charactersheet.domain.clazz.service;

import com.jiubredeemer.charactersheet.integration.RuleBookClient;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClazzIntegrationService {
    private final RuleBookClient ruleBookClient;

    public ClazzDto getClassByCode(String code, UUID roomId) {
        return ruleBookClient.getClassByCode(code, roomId);
    }
}
