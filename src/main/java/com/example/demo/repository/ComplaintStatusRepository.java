package com.example.demo.repository;

import com.example.demo.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
    List<ComplaintStatus> findByComplaintIdOrderByUpdatedOnDesc(Long complaintId);
}
