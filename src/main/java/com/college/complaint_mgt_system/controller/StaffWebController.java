package com.college.complaint_mgt_system.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.complaint_mgt_system.model.Complaint;
import com.college.complaint_mgt_system.service.ComplaintService;
@Controller
@RequestMapping("/staff")
public class StaffWebController {
    @Autowired
    private ComplaintService complaintService;
    @GetMapping("/assigned-complaints")
    public String viewAssignedComplaints(Model model, Authentication authentication) {
        String email = authentication.getName();
        List<Complaint> complaints = complaintService.getComplaintsByAssignedFacultyEmail(email);
        model.addAttribute("complaints", complaints);
        model.addAttribute("title", "Assigned Complaints (Staff View)");
        return "complaint-list-staff"; 
    }

    @PostMapping("/mark-complete")
    public String markComplaintComplete(@RequestParam("complaintId") Long complaintId) {
        complaintService.updateComplaintStatus(complaintId, Complaint.Status.RESOLVED);
        return "redirect:/staff/assigned-complaints"; // redirect back to staff view

    }
    @PostMapping("/add-remarks")
    public String addRemarks(@RequestParam Long complaintId,@RequestParam String remarks) {
        Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow();
        complaint.setRemarks(remarks);
        complaint.setStatus(Complaint.Status.RESOLVED);
        complaintService.updateComplaint(complaint);
        return "redirect:/staff/assigned-complaints?email=" + complaint.getAssignedFacultyEmail();
    }
}