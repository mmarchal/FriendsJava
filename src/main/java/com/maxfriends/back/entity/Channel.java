package com.maxfriends.back.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "channel_friend_table",foreignKey = @ForeignKey(name = "FK_channel_friend"),
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name="channel_id"))
    private Collection<Friend> friends;

    @OneToMany(mappedBy = "channel")
    private Collection<Message> messagesList;

}
