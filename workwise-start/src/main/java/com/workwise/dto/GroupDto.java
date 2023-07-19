package com.workwise.dto;

import lombok.*;

@Builder
@Data
public class GroupDto {

    private String admin;
    private String groupName;
    private String description;
    private Boolean visible;
}
