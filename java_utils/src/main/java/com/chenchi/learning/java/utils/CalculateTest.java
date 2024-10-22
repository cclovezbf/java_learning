package com.chenchi.learning.java.utils;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.junit.Test;

public class CalculateTest {
    public static void main(String[] args) {
        String expressionString = "1+(-1+2.0-3.0+(4.0-5.0))+3.1-4.1+2*3+1.1*4";
        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(expressionString);
        Object evaluate = jexlExpression.evaluate(null);
        System.out.println(evaluate);
    }
}
