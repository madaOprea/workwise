package com.user.repository;

import com.user.model.Friendship;
import com.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("friendshipRepository")
public interface FriendshipRepository extends JpaRepository<Friendship, String> {

    List<Friendship> getFriendshipByUserId(String userId);
    Friendship getFriendshipByUserIdAndFriendId(String userId, String friendId);
    Friendship getFriendshipByUserAndFriend(User user, User friend);

    @Query(value = "select u from User u join Friendship f on f.user = u.id " +
                    "where f.user = :userId and f.friend = :friendId and u.active = true")
    Friendship getActiveFriends(String userId);
}
