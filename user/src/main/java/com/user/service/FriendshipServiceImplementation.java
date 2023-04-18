package com.user.service;

import com.user.model.*;
import com.user.repository.*;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Log
@Service
public class FriendshipServiceImplementation implements FriendshipService {

    private FriendshipRepository friendshipRepository;

    private UserRepository userRepository;

    @Override
    public List<Friendship> getFriendsByUserId(String userId) {
        return friendshipRepository.getFriendshipByUserId(userId);
    }

    @Override
    public Friendship getFriendshipByUserIdAndFriendId(String userId, String friendId) {
        log.info(" *** " + this.getClass() + " *** getFriendshipByUserIdAndFriendId ");

        return friendshipRepository.getFriendshipByUserIdAndFriendId(userId, friendId);
    }

    @Override
    public Friendship getFriendshipByUserAndFriend(User user, User friend) {
        log.info(" *** " + this.getClass() + " *** getFriendshipByUserAndFriend ");

        return friendshipRepository.getFriendshipByUserAndFriend(user, friend);
    }

    @Override
    public Friendship getActiveFriends(String userId) {
        return friendshipRepository.getActiveFriends(userId);
    }

    @Override
    public Friendship changeFriendship(String userId, String friendId, String status) {
        log.info(" *** " + this.getClass() + " *** changeFriendship ");

        User user = userRepository.findUserById(userId);
        User friend = userRepository.findUserById(friendId);

       Status friendshipStatus = Status.valueOf(status.toUpperCase());
       Friendship friendship = Friendship.builder()
                .user(user)
                .friend(friend)
                .status(friendshipStatus)
                .build();
        friendshipRepository.save(friendship);

        return friendship;
    }

    @Override
    public void unfriend(String userId, String friendId) {
        log.info(" *** " + this.getClass() + " *** unfriend ");

        Friendship friendship = friendshipRepository.getFriendshipByUserIdAndFriendId(userId, friendId);
        friendshipRepository.delete(friendship);
    }
}