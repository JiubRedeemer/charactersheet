package com.jiubredeemer.charactersheet.domain.util;

import com.jiubredeemer.charactersheet.domain.ability.service.AbilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Stack;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class HpCalculator {
    private final AbilityService abilityService;

    public Long calculate(String expression, UUID characterId) {
        // Замена кодов характеристик на их значения
        String parsedExpression = replaceCharacteristics(expression, characterId);

        // Вычисление значения выражения
        return evaluateExpression(parsedExpression);
    }

    private String replaceCharacteristics(String expression, UUID characterId) {
        Pattern pattern = Pattern.compile("[A-Z]{3}");
        Matcher matcher = pattern.matcher(expression);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String characteristicCode = matcher.group();
            Long value = abilityService.getValueByCode(characterId ,characteristicCode);
            matcher.appendReplacement(sb, String.valueOf(value));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    private Long evaluateExpression(String expression) {
        // Простое вычисление математического выражения (поддержка +, -, *, /)
        return evaluate(expression);
    }

    private Long evaluate(String expression) {
        Stack<Long> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        long n = 0L;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                n = n * 10 + (ch - '0');
            } else if (isOperator(ch)) {
                numbers.push(n);
                n = 0L;

                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    Long b = numbers.pop();
                    Long a = numbers.pop();
                    numbers.push(applyOperation(a, b, operators.pop()));
                }
                operators.push(ch);
            }
        }

        numbers.push(n);
        while (!operators.isEmpty()) {
            Long b = numbers.pop();
            Long a = numbers.pop();
            numbers.push(applyOperation(a, b, operators.pop()));
        }

        return numbers.pop();
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }

    private Long applyOperation(Long a, Long b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> b != 0 ? a / b : 0; // Защита от деления на ноль
            default -> throw new IllegalArgumentException("Unsupported operator: " + op);
        };
    }

    public interface CharacteristicService {
        int getValueByCode(String code);
    }
}
