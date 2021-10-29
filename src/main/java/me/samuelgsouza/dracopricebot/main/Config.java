package me.samuelgsouza.dracopricebot.main;

public class Config {

    Config(){

    }

    public static String get(String key){
        return System.getenv(key.toUpperCase());
    }

}
