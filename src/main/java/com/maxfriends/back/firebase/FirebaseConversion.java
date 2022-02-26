package com.maxfriends.back.firebase;

import com.google.firebase.auth.UserRecord;
import com.maxfriends.back.entity.Friend;

public class FirebaseConversion {

    public UserRecord.CreateRequest convertFriendToUser(Friend friend) {
        UserRecord.CreateRequest user = new UserRecord.CreateRequest();
        user.setEmail(friend.getEmail());
        user.setDisplayName(friend.getPrenom());
        user.setPassword(friend.getPassword());
        return user;
    }
}
