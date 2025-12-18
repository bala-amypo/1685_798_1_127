package com.example.demo.controller;

import com.example.demo.service.PriorityRuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rules")
public class PriorityRuleController {

    private final PriorityRuleService service;

    public PriorityRuleController(PriorityRuleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Object getAllRules() {
        return service.getAllRules();
    }
}
