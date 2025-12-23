package com.example.demo.controller;

import com.example.demo.service.PriorityRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rules")
public class PriorityRuleController {

    private final PriorityRuleService service;

    public PriorityRuleController(PriorityRuleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Object getRules() {
        return service.getActiveRules();
    }
}
