package com.college.complaint_mgt_system.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.complaint_mgt_system.model.Complaint;
import com.college.complaint_mgt_system.service.ComplaintService;
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private ComplaintService complaintService;
    @GetMapping("/all-complaints")
    public ResponseEntity<List<Complaint>> viewAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }
    @PutMapping("/assign-complaint")
    public ResponseEntity<Complaint> assignComplaint(
            @RequestParam Long complaintId,
            @RequestParam String facultyEmail) {
        Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow();
        complaint.setAssignedFacultyEmail(facultyEmail);
        complaintService.updateComplaint(complaint);
        return ResponseEntity.ok(complaint);
    }
}
   