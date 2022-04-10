package com.maxfriends.back.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "last_message")
@IdClass(LastMessageId.class)
public class LastMessage {

    @Id
    Long friendId;

    @Id
    Long channelId;

    @Column
    Long lastMessageId_read;

    @Column
    Long lastMessageId_sent;

    @Column
    LocalDateTime dateLastMessageRead;

    @Column
    LocalDateTime dateLastMessageSent;
}

class LastMessageId implements Serializable {
    private Long friendId;

    private Long channelId;

    public LastMessageId(){}

    public LastMessageId(Long friendId, Long channelId) {
        this.friendId = friendId;
        this.channelId = channelId;
    }
}