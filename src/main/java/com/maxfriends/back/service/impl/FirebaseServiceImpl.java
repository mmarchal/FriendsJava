package com.maxfriends.back.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Value;
import com.maxfriends.back.dto.FirebaseAuthObjectDto;
import com.maxfriends.back.entity.FirebaseConfiguration;
import com.maxfriends.back.security.model.LoginUser;
import com.maxfriends.back.service.IFirebaseService;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service
public class FirebaseServiceImpl implements IFirebaseService {

    @Autowired
    private Environment env;

    @Override
    public FirebaseAuthObjectDto signWithLoginAndPassword(LoginUser loginUser) throws IOException, InterruptedException {
        FirebaseAuthObjectDto firebaseAuthObjectDto = null;
        String url = env.getProperty("firebase.configuration.firebaseLoginUrl");
        String key = env.getProperty("firebase.configuration.firebaseLoginKey");
        String returnSecureToken = env.getProperty("firebase.configuration.firebaseLoginReturnSecureToken");

        HashMap<String, String> values = new HashMap<>() {{
            put("email", loginUser.getUsername());
            put("password", loginUser.getPassword());
            put("returnSecureToken", returnSecureToken);
        }};

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?key=" + key))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            new LogsInformations().affichageLogDate(response.body());
            //firebaseAuthObjectDto = new FirebaseAuthObjectDto();
        } else {
            new LogsInformations().affichageLogDate(String.valueOf(response.statusCode()));
        }
        return firebaseAuthObjectDto;
    }
}
