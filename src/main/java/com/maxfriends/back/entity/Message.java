package com.maxfriends.back.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(name="content")
    private String content;

    @Column
    private LocalDateTime deliveredAt;

    @ManyToOne
    @JoinColumn(columnDefinition = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(columnDefinition = "friend_id")
    private Friend friend;
}
