package com.jiubredeemer.charactersheet.integration.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project-d.integrations.rule-book")
public class RuleBookProperty {
    private String baseUrl;
    private String roomsUrl;
    private String roomRulesUrl;
    private String racesUrl;
    private String classesUrl;
    private String abilitiesUrl;
    private String skillsUrl;
    private String skillsByCodeUrl;
    private String skillsByClassUrl;
}
