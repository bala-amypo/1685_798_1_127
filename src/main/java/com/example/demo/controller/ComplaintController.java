package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;
    private final UserService userService;

    public ComplaintController(ComplaintService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/submit")
    public Object submit(@RequestBody ComplaintRequest request,
                         @RequestParam String email) {

        User user = userService.findByEmail(email);
        return service.submitComplaint(request, user);
    }

    @GetMapping("/user/{userId}")
    public List<?> getUserComplaints(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return service.getComplaintsForUser(user);
    }

    @GetMapping("/prioritized")
    public List<?> prioritized() {
        return service.getPrioritizedComplaints();
    }
}
