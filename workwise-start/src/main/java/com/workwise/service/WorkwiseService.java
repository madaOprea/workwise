package com.workwise.service;

import com.workwise.dto.*;
import com.workwise.model.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@RequiredArgsConstructor
@Data
@Log4j2
@Service
public class WorkwiseService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final BlogService blogService;

    @Autowired
    private final GroupService groupService;

    @Autowired
    private final ChatService chatService;

    public void addUser(UserDto userDto) {
        log.info("*** starting flow from Workwise." + this.getClass());

        // looking for a friend for the new user
        UserResponse friend = friend = userService.getTheLastUser().block();
        if (friend == null) {
            UserDto backUpUser = UserDto.builder().name("test").email("test").build();
            friend = userService.addUser(backUpUser).block();
            log.info("*** starting flow from Workwise." + this.getClass() + " --- The database was empty so now there is one user!");
        }

        // add the new user
        UserResponse newUser = userService.addUser(userDto).block();
        log.info("/n *** starting flow from Workwise." + this.getClass() + " --- new user was added to database");

        //add the new friendship
        FriendshipDto friendshipDto = FriendshipDto.builder()
                .friend(Friend.builder().id(friend.getId()).build())
                .build();
        userService.addFriendship(newUser.getId(), friendshipDto).block();
        log.info("/n *** starting flow from Workwise." + this.getClass() + " --- new friendship was added to database");

        GroupDto newGroup = GroupDto.builder()
                .groupName("testGroup")
                .description("testDescription")
                .admin(newUser.getId())
                .visible(true)
                .build();
        groupService.addGroup(newGroup).block();
        log.info("*** starting flow from Workwise." + this.getClass() + " --- new group was added to database");

        blogService.addBlog();
        log.info("*** starting flow from Workwise." + this.getClass() + " --- new blog was added to database");

        blogService.addComment();
        log.info("*** starting flow from Workwise." + this.getClass() + " --- new comment was added to database");

        File file = new File("src/main/resources/message");
        BufferedReader br = null;
        String privateConversation = null;
        try {
            br = new BufferedReader(new FileReader(file));
            privateConversation = br.readLine();
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("*** starting flow from Workwise." + this.getClass() + " --- FileNotFoundException " + fileNotFoundException.getMessage());
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            log.error("*** starting flow from Workwise." + this.getClass() + " --- IOException " + ioException.getMessage());
            ioException.printStackTrace();
        }

        ChatDto firstChat = ChatDto.builder()
                .sender(newUser.getId())
                .receiver(friend.getId())
                .message(privateConversation)
                .build();
        Chat newChat = chatService.addChat(firstChat).block();
        log.info("/n *** starting flow from Workwise." + this.getClass() + " --- new chat was added to database");

        ChatDto secondChat = ChatDto.builder()
                .receiver(newUser.getId())
                .sender(friend.getId())
                .message(privateConversation)
                .build();
        chatService.replyToChat(newChat.getChatId(), secondChat).block();
        log.info("/n *** starting flow from Workwise." + this.getClass() + " --- new reply was added to database");

    }
}
