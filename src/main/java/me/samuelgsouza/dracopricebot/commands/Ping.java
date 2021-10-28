package me.samuelgsouza.dracopricebot.commands;

import me.samuelgsouza.dracopricebot.main.Bot;
import me.samuelgsouza.dracopricebot.main.Config;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Ping extends ListenerAdapter {

    String prefix = Config.get("prefix");

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");
        TextChannel textChannel = event.getChannel();

        if (args[0].equalsIgnoreCase(prefix+"ping")){
            textChannel.sendMessage(Bot.jda.getGatewayPing() + "ms").queue();
        }
    }
}
