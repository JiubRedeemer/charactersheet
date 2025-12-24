package com.jiubredeemer.charactersheet.domain.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;

public class FormulaCalculator {
    public static Long calculate(String formula, Map<String, Long> vars) {
        Expression exp = new ExpressionBuilder(formula)
                .variables(vars.keySet())
                .build();

        vars.forEach(exp::setVariable);

        return (long) exp.evaluate();
    }
}
