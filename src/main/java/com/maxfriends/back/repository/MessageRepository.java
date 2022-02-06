package com.maxfriends.back.repository;

import com.maxfriends.back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value="SELECT MAX(delivered_at) FROM message where channel_id = :channelId",nativeQuery = true)
    LocalDateTime getLastMessageSentInChannel(@Param("channelId")Long channelId);
}
