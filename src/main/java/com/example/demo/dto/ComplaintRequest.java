package com.example.demo.dto;

import com.example.demo.entity.Complaint;

public class ComplaintRequest {
    public String title;
    public String description;
    public String category;
    public String channel;
    public Complaint.Severity severity;
    public Complaint.Urgency urgency;
}
