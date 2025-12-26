package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.ComplaintStatus;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.ComplaintStatusRepository;
import com.example.demo.service.ComplaintStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintStatusServiceImpl implements ComplaintStatusService {
    
    private final ComplaintStatusRepository complaintStatusRepository;
    private final ComplaintRepository complaintRepository;
    
    public ComplaintStatusServiceImpl(ComplaintStatusRepository complaintStatusRepository, 
                                    ComplaintRepository complaintRepository) {
        this.complaintStatusRepository = complaintStatusRepository;
        this.complaintRepository = complaintRepository;
    }
    
    @Override
    public void updateStatus(Long complaintId, ComplaintStatus.Status status) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        
        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setComplaint(complaint);
        complaintStatus.setStatus(status);
        
        complaintStatusRepository.save(complaintStatus);
    }
    
    @Override
    public List<ComplaintStatus> getStatusHistory(Long complaintId) {
        return complaintStatusRepository.findByComplaintIdOrderByUpdatedOnDesc(complaintId);
    }
}
