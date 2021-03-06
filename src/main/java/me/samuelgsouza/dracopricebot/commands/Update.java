package me.samuelgsouza.dracopricebot.commands;

import me.samuelgsouza.dracopricebot.main.Config;
import me.samuelgsouza.dracopricebot.messages.EmbedMessage;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Update extends ListenerAdapter {

    public Update() {

    }

    String prefix = Config.get("prefix");

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    Set<String> channels = new HashSet<>();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");
        TextChannel textChannel = event.getChannel();

        if (args[0].equalsIgnoreCase(prefix + "update")) {

            channels.add(event.getChannel().getId());

            textChannel.sendMessage("Este canal receberá atualizações a cada 10 segundos!").queue();

        }

        if (args[0].equalsIgnoreCase(prefix + "stopupdate")) {

            channels.removeIf(element -> {
                return element.startsWith(event.getChannel().getId());
            });
            textChannel.sendMessage("Este canal não receberá mais atualizações!").queue();

        }

    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Runnable r = () -> {

            for(String c: channels) {

                String array[] = c.split(":");
                TextChannel channel = event.getJDA().getTextChannelById(array[0]);

                int ChannelIdLenght = 18;

                if (c.length() <= ChannelIdLenght){

                    channel.sendMessageEmbeds(new EmbedMessage().defaultMessage()).queue((message) -> {
                        channels.add(c + ":" + message.getIdLong());
                        channels.remove(c);
                    });

                }else {
                    channel.editMessageEmbedsById(array[1], new EmbedMessage().defaultMessage()).queue();
                }

            }

        };

        executor.scheduleAtFixedRate(r, 1, 10, TimeUnit.SECONDS);
    }
}
