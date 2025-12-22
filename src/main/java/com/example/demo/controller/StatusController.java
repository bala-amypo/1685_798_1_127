package com.example.demo.controller;

import com.example.demo.service.ComplaintStatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final ComplaintStatusService service;

    public StatusController(ComplaintStatusService service) {
        this.service = service;
    }

    @GetMapping("/history/{complaintId}")
    public Object history(@PathVariable Long complaintId) {
        return service.getStatusHistory(complaintId);
    }
}
