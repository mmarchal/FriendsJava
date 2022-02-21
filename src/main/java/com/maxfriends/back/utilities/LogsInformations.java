package com.maxfriends.back.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsInformations {

    Logger logger = LoggerFactory.getLogger(LogsInformations.class);

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



}
