package com.maxfriends.back.utilities;


import com.maxfriends.back.firebase.FirebaseRequest;
import com.maxfriends.back.firebase.FirebaseUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class LogsInformations {

    Logger logger = LoggerFactory.getLogger(LogsInformations.class);

    @Autowired
    FirebaseRequest firebaseRequest = new FirebaseRequest();

    public void affichageLogDate(String data) {
        Date date = new Date();
        SimpleDateFormat sF = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm:ss");
        String affichage = sF.format(date) + " --> " + data;
        logger.info(affichage);
    }

    public String getRandomPassword(int longueur) {
          
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"
                                    + "&é'(-è_çà)=";
  
        StringBuilder sb = new StringBuilder(longueur);
  
        for (int i = 0; i < longueur; i++) {
  
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }

    public void sendToFirebase(String methode, String params, boolean etat) {
        try {
            FirebaseUpdate firebaseUpdate = new FirebaseUpdate();
            firebaseUpdate.setDateModification(LocalDateTime.now());
            firebaseUpdate.setFonctionAppele(methode);
            firebaseUpdate.setEtatRequete(etat);
            if(!params.isEmpty()) firebaseUpdate.setPamametres(params);
            firebaseRequest.saveUpdateDetails(firebaseUpdate);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
