package com.jgeekmz.management_app.configurations;

import com.jgeekmz.management_app.models.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Component
public class MailGunConfig {

    @Autowired private static User user;

    private static final String YOUR_DOMAIN_NAME = "sandbox1cb2e324d78342c8a242d7aa613b1c87.mailgun.org";



    public static JsonNode sendSimpleMessage(HttpServletRequest request2, String email, String token) throws UnirestException {

        // URL for registration
        String appUrl = request2.getScheme() + "://" + request2.getServerName() + ":" + request2.getServerPort() + "/";

        //user.setConfirmationToken(UUID.randomUUID().toString());

        HttpResponse<com.mashape.unirest.http.JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                .basicAuth("api", API.API_KEY)
                .field("from", "noreply@management-app.com")
                .field("to", email)
                .field("subject", "To confirm your e-mail address, please click the link below:\n\n\n" + appUrl + "confirm?token=" + token)
                .field("Registrierung >>> ", "Hier auf den Link anklicken: ")
                .asJson();

        return request.getBody();
    }


}
