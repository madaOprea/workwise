package com.user.controller;

import com.user.model.Friendship;
import com.user.model.User;
import com.user.model.dto.RequestedUserDto;
import com.user.service.FriendshipService;
import com.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@AllArgsConstructor
@Log
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> findAllUsers() {
        log.info("/n ***"  + this.getClass() + " findAllUsers /n ");
        List<User> list = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        log.info("/n ***"  + this.getClass() + " findById /n ");
        User userFound = userService.findUserById(id);
        if (userFound != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userFound);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getLastUser")
    public ResponseEntity<User> findTheLastUserAdded() {
        log.info("/n ***"  + this.getClass() + " findTheLastUserAdded /n ");

        User lastUserAdded = userService.findTheLastUserAdded();
        if (lastUserAdded != null) {
            return ResponseEntity.status(HttpStatus.OK).body(lastUserAdded);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
        log.info("***" + this.getClass() + " findByEmail method /n");

        User userFound;
        try {
            userFound = userService.findUserByEmail(email);
            if (Objects.isNull(userFound)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userFound);
            }
        } catch(Exception e) {
            log.info("***" + this.getClass() + " findByEmail method *** the error is: " + e.getMessage() +"/n");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userFound);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody @Valid RequestedUserDto userDto) {
        log.info("***" + this.getClass() + " signUpAppUser method /n");

        User user;
        try {
            user = userService.signUpAppUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch(Exception e) {
            log.info("***" + this.getClass() + " signUpAppUser method *** the error is: " + e.getMessage() +"/n");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/signin")
    public String signInUser(@RequestBody @Valid RequestedUserDto userDto) {
        log.info("***" + this.getClass() + " signInUser method /n");

        userService.signInUser(userDto.getEmail(), userDto.getPassword());
        return "successfulPage";
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody RequestedUserDto updatedUserDTO) {
        log.info("/n ***" + this.getClass() + " --- updateUser method ");

        User updatedUser = new User();
        try {
            updatedUser = userService.update(id, updatedUserDTO);
        } catch(Exception e) {
            log.info(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        log.info("***" + this.getClass() + " --- deleteUser method /n");

        try {
            userService.deleteById(id);
        } catch(EntityNotFoundException e) {
            log.info(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            log.info(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email) {
        log.info("***" + this.getClass() + " *** forgotPassword method /n");

        userService.forgotPassword(email);

        User forgetfulUser = userService.findUserByEmail(email);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/userId")
                .buildAndExpand(forgetfulUser.getId())

                .toUri();

        return ResponseEntity.created(URI.create("The redirected path is: " + location)).build();
    }

    @DeleteMapping("/{userId}/unfriend/{friendId}")
    public ResponseEntity<Friendship> unfriend(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
        log.info("/n *** " + this.getClass() + " *** unfriend /n");

        friendshipService.unfriend(userId, friendId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{userId}/unregister")
    public ResponseEntity<Friendship> unregister(@PathVariable("userId") String userId) {
        log.info("/n *** " + this.getClass() + " *** unregister /n");

        userService.unregister(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
