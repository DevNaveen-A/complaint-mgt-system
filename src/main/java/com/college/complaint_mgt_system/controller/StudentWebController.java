package com.college.complaint_mgt_system.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.complaint_mgt_system.model.Complaint;
import com.college.complaint_mgt_system.model.Complaint.Status;
import com.college.complaint_mgt_system.service.ComplaintService;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private ComplaintService complaintService;

    // ✅ Show student dashboard with previous complaints
    @GetMapping("/dashboard")
    public String studentDashboard(Principal principal, Model model) {
        String email = principal.getName(); // logged-in student's email
        List<Complaint> complaints = complaintService.getComplaintsByStudentEmail(email);
        model.addAttribute("complaints", complaints);
        return "student-dashboard";
    }

    // ✅ Show complaint submission form
    @GetMapping("/submit-complaint")
    public String showComplaintForm() {
        return "complaint-form";
    }

    // ✅ Handle complaint submission
    @PostMapping("/submit-complaint")
    public String submitComplaint(@RequestParam String subject,
                                   @RequestParam String description,
                                   @RequestParam String category,
                                   Principal principal,
                                   Model model) {

        String email = principal.getName();

        Complaint complaint = new Complaint();
        complaint.setSubject(subject);
        complaint.setDescription(description);
        complaint.setCategory(category);
        complaint.setStudentEmail(email);
        complaint.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        complaint.setStatus(Status.PENDING);

        complaintService.saveComplaint(complaint);

        model.addAttribute("message", "Complaint submitted successfully!");
        return "complaint-form";
    }
}