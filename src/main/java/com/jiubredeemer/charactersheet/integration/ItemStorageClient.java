package com.jiubredeemer.charactersheet.integration;

import com.jiubredeemer.charactersheet.integration.configuration.RuleBookProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ItemStorageClient {
    private final RestClient restClient;
    private final RuleBookProperty ruleBookProperty;

}
