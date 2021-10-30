package me.samuelgsouza.dracopricebot.messages;

import me.samuelgsouza.dracopricebot.core.Rate;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmbedMessage {

    public EmbedMessage() {

    }

    public EmbedBuilder embedBuilder = new EmbedBuilder();

    public MessageEmbed defaultMessage(){
        Rate rate = new Rate();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String date = dtf.format(LocalDateTime.now());

        embedBuilder.setTitle(":sunglasses: Draco Price Bot", "https://github.com/SamuelGSouza/draco-price-bot");
        embedBuilder.setDescription("Preços em USD");
        embedBuilder.setColor(Color.decode("0x0FA5F0"));
        embedBuilder.addField(":hot_face: Draco", "$"+rate.dracoPrice(), true);
        embedBuilder.addField(":thinking: Wemix", "$"+rate.wemixPrice(), true);
        embedBuilder.addField(":slight_smile: Klay", "$"+rate.klayPrice(), true);
        embedBuilder.addField(":clock1: Atualizado em:", date, false);
        embedBuilder.setFooter("https://github.com/SamuelGSouza/draco-price-bot");
        return embedBuilder.build();

    }
}
