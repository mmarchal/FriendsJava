package com.maxfriends.back.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.maxfriends.back.dto.FirebaseAuthObjectDto;
import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.repository.FriendRepository;
import com.maxfriends.back.security.config.JwtTokenUtil;
import com.maxfriends.back.security.model.ApiResponse;
import com.maxfriends.back.security.model.AuthToken;
import com.maxfriends.back.security.model.LoginUser;
import com.maxfriends.back.service.IFirebaseService;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IFirebaseService firebaseService;

    @Autowired
    private FriendRepository friendRepository;

    private LogsInformations logsInformations = new LogsInformations();

    @PostMapping()
    public ApiResponse<AuthToken> register(@RequestBody LoginUser user) {
        FirebaseAuthObjectDto friend = null;
        try {
            friend = firebaseService.signWithLoginAndPassword(user);
        } catch (IOException | InterruptedException e) {
            logsInformations.affichageLogDate("Erreur : " + e.getMessage());
        }
        if(friend!=null) {
            String token = jwtTokenUtil.generateToken(friend.getEmail());
            logsInformations.affichageLogDate("Utilisateur " + friend.getDisplayName() + " connecté !");
            return new ApiResponse<>(200,"OK",new AuthToken(token,friend.getDisplayName(),friend.getLocalId()));
        } else {
            return null;
        }
    }

}
