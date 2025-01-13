package com.jiubredeemer.charactersheet.constants;

import lombok.Getter;

@Getter
public enum LevelInfoEnum {
    LVL_1(1L, 0L, 2),
    LVL_2(2L, 300L, 2),
    LVL_3(3L, 900L, 2),
    LVL_4(4L, 2700L, 2),
    LVL_5(5L, 6500L, 3),
    LVL_6(6L, 14000L, 3),
    LVL_7(7L, 23000L, 3),
    LVL_8(8L, 34000L, 3),
    LVL_9(9L, 48000L, 4),
    LVL_10(10L, 64000L, 4),
    LVL_11(11L, 85000L, 4),
    LVL_12(12L, 100000L, 4),
    LVL_13(13L, 120000L, 5),
    LVL_14(14L, 140000L, 5),
    LVL_15(15L, 165000L, 5),
    LVL_16(16L, 195000L, 5),
    LVL_17(17L, 225000L, 6),
    LVL_18(18L, 265000L, 6),
    LVL_19(19L, 305000L, 6),
    LVL_20(20L, 355000L, 6);

    private final Long level;
    private final Long xp;
    private final Integer proficiencyBonus;

    LevelInfoEnum(Long level, Long xp, Integer proficiencyBonus) {
        this.level = level;
        this.xp = xp;
        this.proficiencyBonus = proficiencyBonus;
    }
}
