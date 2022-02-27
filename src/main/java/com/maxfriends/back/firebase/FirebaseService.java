package com.maxfriends.back.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.maxfriends.back.BackApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class FirebaseService {

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount =
                    this.getClass().getClassLoader().getResourceAsStream("friendchat.json");

            FirebaseOptions options = null;
            if (serviceAccount != null) {
                options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://friendchat-4626f-default-rtdb.europe-west1.firebasedatabase.app")
                        .build();
            }

            if (options != null) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
