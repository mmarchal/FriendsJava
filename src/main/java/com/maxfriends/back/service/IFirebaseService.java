package com.maxfriends.back.service;

import com.maxfriends.back.dto.FirebaseAuthObjectDto;
import com.maxfriends.back.security.model.LoginUser;

import java.io.IOException;

public interface IFirebaseService {

    FirebaseAuthObjectDto signWithLoginAndPassword(LoginUser loginUser) throws IOException, InterruptedException;
}
