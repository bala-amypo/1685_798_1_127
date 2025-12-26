package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaint_status")
public class ComplaintStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
    public enum Status {
        OPEN, IN_PROGRESS, RESOLVED
    }
    
    @PrePersist
    protected void onCreate() {
        updatedOn = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Complaint getComplaint() { return complaint; }
    public void setComplaint(Complaint complaint) { this.complaint = complaint; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public LocalDateTime getUpdatedOn() { return updatedOn; }
    public void setUpdatedOn(LocalDateTime updatedOn) { this.updatedOn = updatedOn; }
}
