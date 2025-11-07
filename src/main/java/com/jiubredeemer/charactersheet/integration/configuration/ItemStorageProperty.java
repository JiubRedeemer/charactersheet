package com.jiubredeemer.charactersheet.integration.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project-d.integrations.itemstorage")
public class ItemStorageProperty {
    private String apiUrl;
    private String baseUrl;
    private String inventoryUrl;
    private String equipUrl;
    private String countUrl;
    private String moneyUrl;
    private String itemsUrl;
    private String searchUrl;
}
