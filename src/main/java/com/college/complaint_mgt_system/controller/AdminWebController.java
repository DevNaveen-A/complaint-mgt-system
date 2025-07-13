package com.college.complaint_mgt_system.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.complaint_mgt_system.model.Complaint;
import com.college.complaint_mgt_system.model.User;
import com.college.complaint_mgt_system.model.User.Role;
import com.college.complaint_mgt_system.repository.UserRepository;
import com.college.complaint_mgt_system.service.ComplaintService;
@Controller
@RequestMapping("/admin")
public class AdminWebController {
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/all-complaints")
    public String viewAllComplaints(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        List<User> staffList = userRepository.findByRole(Role.STAFF); 
        model.addAttribute("complaints", complaints);
        model.addAttribute("staffList", staffList);
        model.addAttribute("title", "All Complaints (Admin View)");
        return "complaint-list-admin-staff"; 
    }
    // Assign a complaint to staff
    @PostMapping("/assign")
    public String assignComplaint(@RequestParam Long complaintId, @RequestParam String staffEmail) {
        Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow();
        complaint.setAssignedFacultyEmail(staffEmail);
        complaint.setStatus(Complaint.Status.IN_PROGRESS);
        complaintService.updateComplaint(complaint);
        return "redirect:/admin/all-complaints";
    }
    // Unassign a complaint
    @PostMapping("/unassign")
    public String unassignComplaint(@RequestParam Long complaintId) {
        Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow();
        complaint.setAssignedFacultyEmail(null);
        complaint.setStatus(Complaint.Status.PENDING);
        complaintService.updateComplaint(complaint);
        return "redirect:/admin/all-complaints";
    }
}