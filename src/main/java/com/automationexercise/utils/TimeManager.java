package com.automationexercise.utils;

public class TimeManager {
    public static String gettimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
    }

}
