package com.college.complaint_mgt_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.complaint_mgt_system.model.Complaint;
import com.college.complaint_mgt_system.repository.ComplaintRepository;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    public List<Complaint> getComplaintsByStudentEmail(String email) {
        return complaintRepository.findByStudentEmail(email);
    }

    public List<Complaint> getComplaintsByFacultyEmail(String email) {
        return complaintRepository.findByAssignedFacultyEmail(email);
    }
    public List<Complaint> getComplaintsByAssignedFacultyEmail(String email) {
    	return complaintRepository.findByAssignedFacultyEmail(email);
    }

    public Complaint updateComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }
    public void unassignComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setAssignedFacultyEmail(null);
        complaintRepository.save(complaint);

    }
    public void updateComplaintStatus(Long complaintId, Complaint.Status status) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        complaintRepository.save(complaint);
    }
}
