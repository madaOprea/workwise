package com.group.controller;

import com.group.model.Group;
import com.group.service.GroupService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllByAdmin(String adminId) {
        log.info("***"  + this.getClass() + " getAllByAdmin /n ");

        List<Group> group = groupService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(group);
    }

    @PostMapping
    public ResponseEntity<Group> saveGroup(@RequestBody @Valid Group group) {
        log.info("***"  + this.getClass() + " saveGroup /n ");

        groupService.save(group);
        return ResponseEntity.status(HttpStatus.OK).body(group);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Group> updateGroup(@PathVariable String groupId, @RequestBody Group updatedGroup) {
        log.info("***" + this.getClass() + " --- updateGroup method ");

        Group groupUpdated = new Group();
        try {
            groupUpdated = groupService.update(groupId, updatedGroup);
        } catch(Exception e) {
            log.error(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(groupUpdated);
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupUpdated);
    }
}
