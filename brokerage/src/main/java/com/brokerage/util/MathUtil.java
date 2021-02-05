package com.brokerage.util;

import java.math.BigDecimal;

/**
 * Created by liukb on 2017/10/11.
 */
public class MathUtil {
    /**
     * 乘法运算
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @return
     */
    public static String multiply(String multiplicand, String multiplier) {
        return new BigDecimal(multiplicand).multiply(new BigDecimal(multiplier)).toString();
    }

    /**
     * 乘法运算
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @param scale        精度
     * @return
     */
    public static String multiply(String multiplicand, String multiplier, int scale) {
        return new BigDecimal(multiplicand).multiply(new BigDecimal(multiplier)).setScale(scale, BigDecimal.ROUND_FLOOR).toString();
    }

    /**
     * 除法运算
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static String divide(String dividend, String divisor, Integer scale) {
        return new BigDecimal(dividend).divide(new BigDecimal(divisor), scale, BigDecimal.ROUND_FLOOR).toString();
    }

    /**
     * 加法运算
     *
     * @param augend 被加数
     * @param addend 加数
     * @return
     */
    public static String add(String augend, String addend) {
        return new BigDecimal(augend).add(new BigDecimal(addend)).toString();
    }

    /**
     * 减法运算
     *
     * @param minuend    被减数
     * @param subtractor 减数
     * @return
     */
    public static String reduce(String minuend, String subtractor) {
        return new BigDecimal(minuend).subtract(new BigDecimal(subtractor)).toString();
    }

    /**
     * 数字比较
     *
     * @param number
     * @param compareNumber
     * @return
     */
    public static int compareTo(String number, String compareNumber) {
        return new BigDecimal(number).compareTo(new BigDecimal(compareNumber));
    }

    /**
     * 多个数相加
     *
     * @param addend
     * @return
     */
    public static String add(String... addend) {
        BigDecimal sum = new BigDecimal("0");
        for (String str : addend) {
            sum = sum.add(new BigDecimal(str));
        }
        return sum.toString();
    }
}
