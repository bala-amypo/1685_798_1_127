package com.example.demo.service.impl;

import com.example.demo.entity.ComplaintStatus;
import com.example.demo.repository.ComplaintStatusRepository;
import com.example.demo.service.ComplaintStatusService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintStatusServiceImpl implements ComplaintStatusService {
    
    private final ComplaintStatusRepository repository;
    
    public ComplaintStatusServiceImpl(ComplaintStatusRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void updateStatus(Long complaintId, ComplaintStatus.Status status) {
        ComplaintStatus cs = new ComplaintStatus();
        cs.setStatus(status);
        repository.save(cs);
    }
    
    @Override
    public List<ComplaintStatus> getStatusHistory(Long complaintId) {
        return repository.findAll();
    }
}
