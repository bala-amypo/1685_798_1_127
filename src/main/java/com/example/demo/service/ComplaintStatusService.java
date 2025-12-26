package com.example.demo.service;

import com.example.demo.entity.ComplaintStatus;
import java.util.List;

public interface ComplaintStatusService {
    void updateStatus(Long complaintId, ComplaintStatus.Status status);
    List<ComplaintStatus> getStatusHistory(Long complaintId);
}
