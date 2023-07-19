package com.group.repository;

import com.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, String> {
}
