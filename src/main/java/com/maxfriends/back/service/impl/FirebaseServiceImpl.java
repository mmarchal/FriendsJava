package com.maxfriends.back.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxfriends.back.dto.FirebaseAuthObjectDto;
import com.maxfriends.back.security.model.LoginUser;
import com.maxfriends.back.service.IFirebaseService;
import com.maxfriends.back.utilities.LogsInformations;
import org.hibernate.cfg.Environment;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Properties;

public class FirebaseServiceImpl implements IFirebaseService {

    Properties properties = Environment.getProperties();

    @Override
    public FirebaseAuthObjectDto signWithLoginAndPassword(LoginUser loginUser) throws IOException, InterruptedException {
        String url = properties.getProperty("firebaseLoginUrl");
        String key = properties.getProperty("firebaseLoginKey");

        HashMap values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put ("occupation", "gardener");
        }};

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()==200) {
            new LogsInformations().affichageLogDate(response.body());
        } else {
            new LogsInformations().affichageLogDate(String.valueOf(response.statusCode()));
        }
        return null;
    }
}
