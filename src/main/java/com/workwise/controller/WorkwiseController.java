package com.workwise.controller;

import com.workwise.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/workwise")
public class WorkwiseController {

    private final WorkwiseService workwiseService;
    private final UserService userService;

}