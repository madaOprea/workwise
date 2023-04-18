package com.group.services;

import com.group.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAll();
    Group save(Group group);
    Group update(String id, Group group);
    void deleteGroupById(String id);
}
