package com.maxfriends.back.firebase;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FirebaseUpdate {

    Long id;

    LocalDateTime dateModification;

    String fonctionAppele;

    Long friendId;

    String pamametres;
}
