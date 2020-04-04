package com.ub.core.base.utils;

public class BooleanUtils {
    public static Boolean checkBoxToBoolean(String checkboxValue){
        if(checkboxValue != null && checkboxValue.equals("on"))
            return true;
        return false;
    }

    public static String booleanTocheckBox(Boolean aBoolean){
        if(aBoolean == null || aBoolean == false)
            return "";
        return "checked=\"on\"";
    }
}
