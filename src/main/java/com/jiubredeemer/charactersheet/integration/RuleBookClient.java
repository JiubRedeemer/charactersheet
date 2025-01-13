package com.jiubredeemer.charactersheet.integration;

import com.jiubredeemer.charactersheet.domain.room.dto.RoomDto;
import com.jiubredeemer.charactersheet.integration.configuration.RuleBookProperty;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import com.jiubredeemer.charactersheet.integration.dto.race.RaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.text.MessageFormat;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RuleBookClient {
    private final RestClient restClient;
    private final RuleBookProperty ruleBookProperty;

    public RaceDto getRaceByCode(String code, UUID roomId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ResponseEntity<RaceDto> response = restClient.post()
                .uri(ruleBookProperty.getBaseUrl() + ruleBookProperty.getRacesUrl() + "/" + code)
                .headers(h -> h.addAll(headers))
                .body(new RoomIdRequestBody(roomId))
                .retrieve()
                .toEntity(RaceDto.class);
        return response.getBody();
    }

    public ClazzDto getClassByCode(String code, UUID roomId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ResponseEntity<ClazzDto> response = restClient.post()
                .uri(ruleBookProperty.getBaseUrl() + ruleBookProperty.getClassesUrl() + "/" + code)
                .headers(h -> h.addAll(headers))
                .body(new RoomIdRequestBody(roomId))
                .retrieve()
                .toEntity(ClazzDto.class);
        return response.getBody();
    }

    public RoomDto getRoomRules(UUID roomId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ResponseEntity<RoomDto> response = restClient.get()
                .uri(ruleBookProperty.getBaseUrl() + MessageFormat.format(ruleBookProperty.getRoomRulesUrl(), roomId))
                .headers(h -> h.addAll(headers))
                .retrieve()
                .toEntity(RoomDto.class);
        return response.getBody();
    }
}
