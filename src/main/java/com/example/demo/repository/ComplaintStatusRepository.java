package com.example.demo.repository;

import com.example.demo.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
}
