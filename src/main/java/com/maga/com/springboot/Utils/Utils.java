package com.maga.com.springboot.Utils;

import org.apache.commons.lang3.StringUtils;

public class Utils {

    public static String getindex(Integer ind) {

        String index = "";

        if (ind < 10) {
            index = StringUtils.leftPad(ind.toString(), 3, "0") ;
        } else if (ind >= 10 && ind <= 99) {
            index = "0" + ind;
        } else if (ind >= 100) {
            index = ind.toString();
        }

        return index;
    }
}
