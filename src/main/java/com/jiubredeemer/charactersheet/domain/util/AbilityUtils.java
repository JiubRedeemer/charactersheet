package com.jiubredeemer.charactersheet.domain.util;

import com.jiubredeemer.charactersheet.domain.character.dto.AbilityShort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AbilityUtils {
    public long getAbilityValueByCode(List<AbilityShort> abilities, String code) {
        return abilities.stream().filter(abilityShort -> code.equals(abilityShort.getCode())).findAny().orElseThrow().getValue();
    }
}
