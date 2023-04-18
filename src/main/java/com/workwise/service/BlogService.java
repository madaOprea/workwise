package com.workwise.service;

import com.workwise.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class BlogService {

    private final WebClient blogWebClientBuilder;

    public BlogService(WebClient.Builder webClient) {
        log.debug("*** creating webClient for Workwise." + this.getClass());

        this.blogWebClientBuilder = webClient.baseUrl("http://localhost:9004/blog").build();
    }

    public Mono<Blog> addBlog() {
        log.debug("*** adding new group from Workwise." + this.getClass());

        return null;
    }

    public Mono<Comment> addComment() {
        log.debug("*** adding new comment from Workwise." + this.getClass());

        return null;
    }
}
