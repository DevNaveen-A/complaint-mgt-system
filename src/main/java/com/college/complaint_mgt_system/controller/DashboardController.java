package com.college.complaint_mgt_system.controller;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
@Controller
public class DashboardController {
	@GetMapping("/dashboard")
	public void redirectToDashboard(HttpServletResponse response, Authentication authentication) throws IOException {
		String role = authentication.getAuthorities().stream()

				.map(GrantedAuthority::getAuthority)

				.findFirst()

				.orElse("");
		String userEmail = authentication.getName(); // Logged-in user's email
		switch (role) {
		case "ROLE_ADMIN":
			response.sendRedirect("/admin/all-complaints");
			break;
		case "ROLE_STAFF":
			response.sendRedirect("/staff/assigned-complaints?email=" + userEmail);
			break;
		case "ROLE_STUDENT":
			response.sendRedirect("/students/dashboard");
		break;
		default:
			response.sendRedirect("/login?error");

		}

	}

	@GetMapping("/dashboard-admin")

	public String adminDashboard() {

		return "dashboard-admin";

	}

	@GetMapping("/dashboard-student")

	public String studentDashboard() {

		return "dashboard-student";

	}

	@GetMapping("/dashboard-staff")

	public String staffDashboard() {

		return "dashboard-staff";

	}

}