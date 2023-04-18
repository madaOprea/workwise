package com.workwise.service;

import com.workwise.dto.GroupDto;
import com.workwise.model.Group;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class GroupService {


    private final WebClient webClient;

    public GroupService(WebClient.Builder webClientBuilder) {
        log.debug("/n *** creating webClient for Workwise." + this.getClass());

        this.webClient = webClientBuilder.baseUrl("http://localhost:9002/group").build();
    }

    public Mono<Group> addGroup(GroupDto groupDto) {
        log.debug("/n *** adding new group from Workwise." + this.getClass());

        return this.webClient.post()
                .uri("/saveGroup").bodyValue(groupDto)
                .retrieve().bodyToMono(Group.class);
    }
}
