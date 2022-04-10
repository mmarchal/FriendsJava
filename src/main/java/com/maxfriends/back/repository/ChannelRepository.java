package com.maxfriends.back.repository;

import com.maxfriends.back.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Collection<Channel> getChannelByFriendsId(Long friendsId);
}