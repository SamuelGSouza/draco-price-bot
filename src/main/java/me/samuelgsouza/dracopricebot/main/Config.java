package me.samuelgsouza.dracopricebot.main;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    Config(){

    }

    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key){
        return dotenv.get(key.toUpperCase());
    }

}
