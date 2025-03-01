package com.jiubredeemer.charactersheet.domain.characterBio.service;

import com.jiubredeemer.charactersheet.dal.repository.CharacterBioRepository;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.SectionEnum;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioUpdateRequest;
import com.jiubredeemer.charactersheet.domain.characterBio.mapper.CharacterBioDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterBioService {
    private static final Logger log = LoggerFactory.getLogger(CharacterBioService.class);
    private final CharacterBioDtoMapper mapper;
    private final CharacterBioRepository repository;

    public CharacterBioDto saveCharacterBio(CharacterBioDto characterBioDto) {
        return mapper.toDto(repository.save(mapper.toEntity(characterBioDto, true)));
    }

    public CharacterBioDto findByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findById(characterId)
                .orElseThrow(() -> new NotFoundException("CharacterBio not found by characterId: " + characterId)));
    }

    public List<CharacterBioDto> findByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }

    public void updateBio(UUID id, String section, CharacterBioUpdateRequest characterBioUpdateRequest) {
        final SectionEnum selectedSection = SectionEnum.valueOf(StringUtils.upperCase(section));
        final CharacterBioDto characterBio = findByCharacterId(id);
        switch (selectedSection) {
            case AGE -> characterBio.setAge(Long.valueOf(characterBioUpdateRequest.getValue()));
            case HEIGHT -> characterBio.setHeight(Long.valueOf(characterBioUpdateRequest.getValue()));
            case WEIGHT -> characterBio.setWeight(Long.valueOf(characterBioUpdateRequest.getValue()));
            case ATTACHMENTS -> characterBio.setAttachments(characterBioUpdateRequest.getValue());
            case HISTORY -> characterBio.setHistory(characterBioUpdateRequest.getValue());
            case IDEALS -> characterBio.setIdeals(characterBioUpdateRequest.getValue());
            case PERSONALITY -> characterBio.setPersonality(characterBioUpdateRequest.getValue());
            case RELATIONSHIPS -> characterBio.setRelationships(characterBioUpdateRequest.getValue());
            case WEAKNESSES -> characterBio.setWeaknesses(characterBioUpdateRequest.getValue());
            default -> log.error("Invalid section: {}", selectedSection);
        }
        repository.save(mapper.toEntity(characterBio, false));
    }
}
