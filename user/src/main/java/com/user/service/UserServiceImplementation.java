package com.user.service;

import com.user.mapper.UserMapper;
import com.user.model.*;
import com.user.model.dto.RequestedUserDto;
import com.user.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Log4j2
@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private EmailService emailService;

    private FriendshipRepository friendshipRepository;
    private FriendshipService friendshipService;

// HELP:
//    private String redirectUserURL;
//    @Value("${redirectUser.path}")
//    public void setRedirectUserURL(String url) {
//        this.redirectUserURL = url;
//    }

    @Override
    public List<User> findAll() {
        log.info(" *** " + this.getClass() + " *** findAll /n");
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String userId) {
        log.info(" *** " + this.getClass() + " *** findUserById /n");
        return userRepository.findUserById(userId);
    }

    @Override
    public User findTheLastUserAdded() {
        log.info(" *** " + this.getClass() + " *** findTheLastUserAdded /n");

        return userRepository.findTheLastUserAdded();
    }

    @Override
    public User findUserByEmail(String email) {
        log.debug(" *** " + this.getClass() + " *** findByEmail /n");
        return userRepository.getByEmail(email);
    }

    @Override
    public User signUpAppUser(RequestedUserDto userDto) {
        log.debug(" *** " + this.getClass() + " *** signUpAppUser /n");

        User newUser = userMapper.toUser(userDto);
        newUser.setActive(true);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setLastModifiedDate(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    @Override
    public void signInUser(String email, String password) {
        log.debug(" *** " + this.getClass() + " *** signInUser /n");

        User user = userRepository.findUserByEmail(email);

        String decodedPasswordUser = Base64.getDecoder().decode(user.getEncodedPassword()).toString();
        if (user.getEncodedPassword().equals(decodedPasswordUser)) {
            log.info(" *** " + this.getClass() + " *** signInUser ");
            user.setActive(true);
            userRepository.save(user);
        } else {
            log.error(" *** " + this.getClass() + " *** signInUser /n");
            throw new EntityNotFoundException(user.getName());
        }
    }

    @Override
    public User update(String id, RequestedUserDto userToUpdate) {
        log.debug(" *** " + this.getClass() + " *** deleteById ");

        if (!exists(id)) {
            throw new EntityNotFoundException(id);
        }
        User userUpdated = userRepository.findUserById(id);
        if (userToUpdate.getName() != null) {
            userUpdated.setName(userToUpdate.getName());
        }
        if (userToUpdate.getPassword() != null) {
            userUpdated.setEncodedPassword(userToUpdate.getPassword());
        } //encode needed
        if (userToUpdate.getEmail() != null) {
            userUpdated.setEmail(userToUpdate.getEmail());
        }
        userUpdated.setLastModifiedDate(LocalDateTime.now());
        userRepository.save(userUpdated);

        return userUpdated;
    }

    @Override
    public void deleteById(String id) {
        log.debug(" *** " + this.getClass() + " *** deleteById ");

        if (!exists(id)) {
            throw new EntityNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public void forgotPassword(String email) {
        log.debug(" *** " + this.getClass() + " *** forgotPassword ");

        if (email == null) {//if (!EmailValidator(email)) {
            throw new EntityNotFoundException("Invalid email address");
        }

        RequestedUserDto requestedUserDto = userMapper.toRequestedUserDto(userRepository.findUserByEmail(email));
        if (requestedUserDto == null) {
            throw new EntityNotFoundException("User not found!");
        }

        String userId = userMapper.toUser(requestedUserDto).getId();
        String redirectUserURL = "http://localhost:8080/reset-password?token=";
        String resetURL = redirectUserURL + userId;
       // emailService.sendPasswordResetEmail(requestedUserDto, resetURL);
    }

    @Override
    public void unregister(String userId) {
        log.debug(" *** " + this.getClass() + " *** unregister ");

        // keep the user in database but with default info
        User userWithNoAccount = userRepository.findById(userId).get();
        userWithNoAccount.setEncodedPassword(Base64.getEncoder().toString());
        userWithNoAccount.setEmail("");
        userRepository.save(userWithNoAccount);

        // unfriend the world
        List<Friendship> friendsList = friendshipService.getFriendsByUserId(userId);
        friendsList.stream().forEach(friendship -> friendship.setStatus(Status.BLOCKED));
    }

    @Override
    public boolean exists(String id) {
        log.debug(" *** " + this.getClass() + " *** exists ");

        return userRepository.findById(id).isPresent();
    }
}
