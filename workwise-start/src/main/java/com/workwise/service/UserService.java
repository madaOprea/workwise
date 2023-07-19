package com.workwise.service;

import com.workwise.dto.FriendshipDto;
import com.workwise.dto.UserDto;
import com.workwise.model.Friendship;
import com.workwise.model.UserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@Service
public class UserService {

    private final WebClient userWebClientBuilder;

    public UserService(WebClient.Builder webClient) {
        log.debug("/n *** creating webClient for Workwise." + this.getClass());

        this.userWebClientBuilder = webClient.baseUrl("http://localhost:9001").build();
    }

    public Mono<List<UserResponse>> getAllUsers() {
        log.debug("/n *** get all users from Workwise." + this.getClass());

        return userWebClientBuilder.get()
                .uri("/user/getAllUsers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserResponse>>() {});
    }

    public Mono<UserResponse> getTheLastUser() {
        log.debug("*** get the last user from Workwise." + this.getClass());

        return this.userWebClientBuilder.get()
                .uri("/user/getLastUser")
                .retrieve().bodyToMono(UserResponse.class);
    }

    public Mono<UserResponse> addUser(UserDto userDto) {
        log.debug("*** add new user from Workwise." + this.getClass());

        return this.userWebClientBuilder.post()
                .uri("/user/signup").bodyValue(userDto)
                .retrieve().bodyToMono(UserResponse.class);
    }

    public Mono<Friendship> addFriendship(String userId, FriendshipDto friendshipDto) {
        log.debug("*** add new friendship from Workwise." + this.getClass());

        return this.userWebClientBuilder.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/friendship/{userId}")
                        .build(userId))
                .bodyValue(friendshipDto)
                .retrieve().bodyToMono(Friendship.class);
    }
}
