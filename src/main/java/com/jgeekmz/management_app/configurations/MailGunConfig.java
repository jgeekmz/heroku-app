package com.jgeekmz.management_app.configurations;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;


@Component
public class MailGunConfig {

    private static final String YOUR_DOMAIN_NAME = "sandbox1cb2e324d78342c8a242d7aa613b1c87.mailgun.org";

    public static JsonNode sendSimpleMessage() throws UnirestException {

        HttpResponse<com.mashape.unirest.http.JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                .basicAuth("api", API.API_KEY)
                .field("from", "Excited User Marton")
                .field("to", "martin.zlatkov1991@gmail.com")
                .field("subject", "Bitte den Link folgen, damit Sie die Registrierung abschliessen!")
                .field("Registrierung >>> ", "Hier auf den Link anklicken: ")
                .asJson();

        return request.getBody();
    }


}
