package me.samuelgsouza.dracopricebot.commands;

import me.samuelgsouza.dracopricebot.main.Config;
import me.samuelgsouza.dracopricebot.messages.EmbedMessage;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class Draco extends ListenerAdapter {

    public Draco() {

    }

    String prefix = Config.get("prefix");

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        TextChannel textChannel = event.getChannel();

        if (args[0].equalsIgnoreCase(prefix + "draco")){
            event.getChannel().sendMessageEmbeds(new EmbedMessage().defaultMessage()).queue();
        }
    }
}
