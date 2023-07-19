package com.group.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="group_uuid")
    private String groupId;

    @Column
    private String admin;

    @Column
    private String groupName;

    @Column
    private String description;

    @Column
    private Boolean visible;
}
