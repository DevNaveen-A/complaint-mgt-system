package com.college.complaint_mgt_system.controller;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.complaint_mgt_system.model.Complaint;
import com.college.complaint_mgt_system.model.Complaint.Status;
import com.college.complaint_mgt_system.service.ComplaintService;
@RestController
@RequestMapping("/api/students")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    @Autowired
    private ComplaintService complaintService;
    @PostMapping("/submit")

    public ResponseEntity<Complaint> raiseComplaint(@RequestBody Complaint complaint) {

        complaint.setStatus(Status.PENDING);

        complaint.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(complaintService.saveComplaint(complaint));

    }

    @GetMapping("/my-complaints")

    public ResponseEntity<List<Complaint>> viewMyComplaints(@RequestParam String email) {

        return ResponseEntity.ok(complaintService.getComplaintsByStudentEmail(email));

    }

}