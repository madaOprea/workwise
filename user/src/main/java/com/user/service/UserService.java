package com.user.service;

import com.user.model.User;
import com.user.model.dto.RequestedUserDto;

import java.util.*;

public interface UserService {

    List<User> findAll();
    User findUserById(String id);
    User findTheLastUserAdded();
    User findUserByEmail(String email);
    User signUpAppUser(RequestedUserDto user);
    void signInUser(String email, String password);
    User update(String id, RequestedUserDto userDto);
    void deleteById(String id);
    void forgotPassword(String email);
    void unregister(String userId);

    boolean exists(String id);


}
