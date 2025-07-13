package com.college.complaint_mgt_system.repository;
import com.college.complaint_mgt_system.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStudentEmail(String studentEmail);
    List<Complaint> findByAssignedFacultyEmail(String assignedFacultyEmail);
}
