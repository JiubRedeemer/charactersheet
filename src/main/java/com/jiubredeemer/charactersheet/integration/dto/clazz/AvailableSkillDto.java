package com.jiubredeemer.charactersheet.integration.dto.clazz;

import com.jiubredeemer.charactersheet.constants.SkillTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class AvailableSkillDto {
    private SkillTypeEnum type;
    private Integer count;
    private List<String> of;
}
