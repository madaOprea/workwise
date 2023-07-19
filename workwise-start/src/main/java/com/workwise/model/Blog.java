package com.workwise.model;

import lombok.*;

import java.util.*;

@Builder
@Data
public class Blog {

    private String userId;
    private String title;
    private String visibility;
    private Set<Comment> commentSet;
}
