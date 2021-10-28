package me.samuelgsouza.dracopricebot.main;

import me.samuelgsouza.dracopricebot.commands.Draco;
import me.samuelgsouza.dracopricebot.commands.Ping;
import me.samuelgsouza.dracopricebot.commands.Update;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot {

    public  static JDA jda;

    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.create(Config.get("TOKEN"),
                EnumSet.allOf(GatewayIntent.class)).build();

        jda.addEventListener(new Ping());
        jda.addEventListener(new Draco());
        jda.addEventListener(new Update());
    }

    public Bot() throws LoginException {
    }
}
