package me.samuelgsouza.dracopricebot.core;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class Rate {
    DecimalFormat df = new DecimalFormat("#,##0.00");

    public JSONObject getRate() {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .uri(URI.create("https://api.mir4global.com/wallet/prices/draco/lastest"))
                .headers("Accept", "application/json")
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(response.body());
        jsonObject = jsonObject.getJSONObject("Data");

        return jsonObject;

    }

    public String dracoPrice() {
        Double dracoPrice = Double.parseDouble(getRate().getString("USDDracoRate"));

        return df.format(dracoPrice);
    }

    public String klayPrice(){
        Double klayPrice = Double.parseDouble(getRate().getString("USDKLAYRate"));

        return df.format(klayPrice);
    }

    public String wemixPrice(){
        Double klayPrice = Double.parseDouble(getRate().getString("USDWemixRate"));

        return  df.format(klayPrice);
    }

    public String date() {
        String dateString = getRate().getString(("CreatedDT"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = null;

        try {
            date = sdf.parse(dateString);
        } catch (ParseException e){
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, -3);

        date = c.getTime();
        sdf.applyPattern("dd/MM/yyyy HH:mm");

        return sdf.format(date);

    }
}
