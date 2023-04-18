package com.user.service;

import com.user.model.Friendship;
import com.user.model.User;

import java.util.*;

public interface FriendshipService {
    List<Friendship> getFriendsByUserId(String userId);
    Friendship getFriendshipByUserIdAndFriendId(String userId, String friendId);
    Friendship getFriendshipByUserAndFriend(User user, User friend);
    Friendship getActiveFriends(String userId);
    void unfriend(String userId, String friendId);

    Friendship changeFriendship(String userId, String friendId, String status);
}
