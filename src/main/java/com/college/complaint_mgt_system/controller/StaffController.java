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
@RequestMapping("/api/staff")
@PreAuthorize("hasRole('STAFF')")
public class StaffController {
    @Autowired
    private ComplaintService complaintService;
    @GetMapping("/assigned-complaints")
    public ResponseEntity<List<Complaint>> viewAssignedComplaints(@RequestParam String facultyEmail) {
        return ResponseEntity.ok(complaintService.getComplaintsByFacultyEmail(facultyEmail));
    }
    @PutMapping("/add-remarks")
    public ResponseEntity<Complaint> addRemarks(@RequestParam Long complaintId,@RequestParam String remarks) {
   Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow();
        complaint.setRemarks(remarks);
        complaintService.updateComplaint(complaint);
        return ResponseEntity.ok(complaint);
    }
}