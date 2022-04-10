package com.maxfriends.back.repository;

import com.maxfriends.back.entity.LastMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastMessageRepository extends JpaRepository<LastMessage, Long> {
    LastMessage findByFriendIdAndChannelId(Long friendId, Long channelId);
}