package com.college.complaint_mgt_system.model;
import jakarta.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String description;
    private String category;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String studentEmail;
    private String assignedFacultyEmail;
    private String remarks;
    @Column(name = "created_at")
    private Timestamp createdAt;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getAssignedFacultyEmail() {
		return assignedFacultyEmail;
	}
	public void setAssignedFacultyEmail(String assignedFacultyEmail) {
		this.assignedFacultyEmail = assignedFacultyEmail;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public enum Status {
        PENDING, IN_PROGRESS, RESOLVED
    }  
}

