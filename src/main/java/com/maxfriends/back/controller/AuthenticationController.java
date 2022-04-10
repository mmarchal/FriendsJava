package com.maxfriends.back.controller;

import com.google.firebase.auth.FirebaseAuth;
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
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        try {
            firebaseService.signWithLoginAndPassword(user);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Friend friend = friendRepository.findByLogin(user.getUsername());
        String token = jwtTokenUtil.generateToken(friend.getLogin());
        logsInformations.affichageLogDate("Utilisateur " + friend.getPrenom() + " connecté !");
        return new ApiResponse<>(200,"OK",new AuthToken(token,friend.getPrenom(),friend.getUid()));
    }

}
