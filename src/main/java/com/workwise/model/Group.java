package com.workwise.model;

import lombok.*;

@Builder
@Data
public class Group {

    private String id;
    private String admin;
    private String groupName;
    private String description;
    private Boolean visible;
}
