package com.example.demo.repository;

import com.example.demo.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {

    List<ComplaintStatus> findByComplaintId(Long complaintId);
}
