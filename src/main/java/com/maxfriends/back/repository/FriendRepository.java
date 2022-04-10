package com.maxfriends.back.repository;

import com.maxfriends.back.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "select * from friend where login = :login", nativeQuery = true)
    Friend findByLogin(@Param(value = "login") String login);

    @Query(value = "select * from friend where uid = :uid", nativeQuery = true)
    Friend getByUid(@Param(value = "uid") String uid);
}
