package com.user.controller;

import com.user.model.*;
import com.user.service.FriendshipService;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@Log
@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    @Autowired
    private final FriendshipService friendshipService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Friendship>> getFriendsByUserId(@PathVariable("userId") String userId) {
        log.info("/n *** " + this.getClass() + " *** getFriendsByUserId /n");

        List<Friendship> friendsList;
        try {
            friendsList = friendshipService.getFriendsByUserId(userId);
            if (friendsList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(friendsList);
    }

    @GetMapping("/{userId}/{friendId}")
    public ResponseEntity<Friendship> getFriendshipByUserIdAndFriendId(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
        log.info("/n *** " + this.getClass() + " *** getFriendshipByUserIdAndFriendId /n");

        Friendship friendship;
        try {
            friendship = friendshipService.getFriendshipByUserIdAndFriendId(userId, friendId);
            if (friendship == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            log.info(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(friendship);
    }

    @GetMapping("/getAllActiveFriends")
    public ResponseEntity<Friendship> getAllActiveFriends(@RequestParam("userId") String userId) {
        log.info("/n *** " + this.getClass() + " *** getAllActiveFriends /n");

        Friendship friends = friendshipService.getActiveFriends(userId);
        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Friendship> sendFriendRequest(@PathVariable("userId") String userId, @RequestBody Friendship friendshipDto) {
        log.info("/n *** " + this.getClass() + " *** sendFriendRequest /n");

        Friendship friendshipRequest = friendshipService.changeFriendship(userId, friendshipDto.getFriend().getId(), Status.PENDING.getStatusFriendship());
        return ResponseEntity.status(HttpStatus.OK).body(friendshipRequest);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Friendship> acceptFriendRequest(@PathVariable("userId") String userId, @RequestBody Friendship friendshipDto) {
        log.info("/n *** " + this.getClass() + " *** acceptFriendRequest");

        Friendship friendship = friendshipService.changeFriendship(userId, friendshipDto.getFriend().getId(), Status.ACCEPTED.getStatusFriendship());
        return ResponseEntity.status(HttpStatus.OK).body(friendship);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Friendship> rejectFriendship(@PathVariable("userId") String userId, @RequestBody Friendship friendshipDto) {
        log.info("/n *** " + this.getClass() + " *** rejectFriendship /n");

        Friendship friendship = friendshipService.changeFriendship(userId, friendshipDto.getFriend().getId(), Status.REJECTED.getStatusFriendship());
        return ResponseEntity.status(HttpStatus.OK).body(friendship);
    }
}
