Complaint Management System - College Project
=============================================

Overview:
---------
This is a web-based Complaint Management System developed using Spring Boot and Thymeleaf. 
It is designed to allow students to raise complaints and track their status, while admins and staff manage them accordingly.

Tech Stack:
-----------
- Java 17
- Spring Boot
- Thymeleaf
- Maven
- MySQL (or H2)
- HTML + CSS (with background image and favicon support)

Features:
---------
1. **Login System**:
   - Admin, Staff, and Student role-based login system.

2. **Student Functions**:
   - Raise a complaint with subject, category, description.
   - View previously submitted complaints.
   - Logout functionality.

3. **Admin Functions**:
   - View all complaints.
   - Assign complaints to staff.
   - Add remarks and update status.
   - Unassign if needed.

4. **Staff Functions**:
   - View assigned complaints.
   - Update complaint status and remarks.

Folder Structure:
-----------------
- `src/main/java`         - Java source code (controllers, services, models).
- `src/main/resources/`
    - `static/`           - Static assets like images (`college.jpg`, `logo.ico`).
    - `templates/`        - Thymeleaf HTML templates (`login.html`, `dashboard-student.html`, etc.).
- `application.properties` - DB configuration and server settings.

How to Run:
-----------
1. Import into Eclipse/IntelliJ as a Maven project.
2. Configure `application.properties` with your database details.
3. Run `ComplaintMgtSystemApplication.java`.
4. Open your browser and go to:
   - `http://localhost:8085/login`

Notes:
------
- Make sure the image files are placed correctly under `static/images/`.
- Use correct email ID during login to match roles.
- Sample image used: `college.jpg` as background.
- Favicon supported via `logo.ico`.

Credits:
--------
Developed by Naveen Kumar  
Java Full Stack Developer  
Year: 2025
