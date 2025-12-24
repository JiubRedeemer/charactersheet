package com.jiubredeemer.charactersheet.domain.util;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class DiceUtils {
    private static final SecureRandom random = new SecureRandom();

    public static Map<String, Long> getRandomDiceMap() {
        Map<String, Long> diceMap = new HashMap<>();

        // Генерация случайного значения для d1..d20 (можно расширить)
        for (int i = 1; i <= 20; i++) {
            String diceName = "d" + i;
            long roll = rollDice(i);
            diceMap.put(diceName, roll);
        }
        diceMap.put("d100", rollDice(100));

        // Проверим
        diceMap.forEach((k, v) -> System.out.println(k + " = " + v));
        return diceMap;
    }

    // Метод броска кубика dN
    private static long rollDice(int sides) {
        return 1 + random.nextInt(sides); // от 1 до sides включительно
    }
}
