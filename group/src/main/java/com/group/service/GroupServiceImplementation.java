package com.group.service;

import com.group.model.Group;
import com.group.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImplementation implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImplementation(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(String id, Group group) {
        groupRepository.save(group);
        return group;
    }

    @Override
    public void deleteGroupById(String id) {
        groupRepository.deleteById(id);
    }
}
