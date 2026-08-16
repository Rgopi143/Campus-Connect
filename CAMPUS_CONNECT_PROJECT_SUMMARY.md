# Smart Campus Management & Assistance Application (Campus Connect)
**Prepared by RANBIDGE Solutions Private Limited**  
*For Narasaraopeta Engineering College*

---

### Development Team
- **R GOPINATH REDDY**
- **V KONDA REDDY**

---

### Project Description
The **Smart Campus Management & Assistance Application (Campus Connect)** is an all-in-one digital platform prepared by **RANBIDGE Solutions Private Limited** for **Narasaraopeta Engineering College**. Developed by **R GOPINATH REDDY** and **V KONDA REDDY**, the project aims to provide students, faculty, administrators, security personnel, and campus staff with an interactive, multi-role mobile and desktop application to streamline daily institutional operations, academic schedules, campus commerce, and administrative approval workflows.

The platform replaces traditional, fragmented paper-based campus processes with an automated, unified digital ecosystem. It supports multi-level digital outpass issuance with encrypted QR code verification for gate security, digital certificate applications with multi-tier approvals, smart canteen meal pre-booking with instant token generation, custom document print queuing, stationery store ordering, Training & Placement Cell (TPC) recruitment drive tracking, interactive academic timetables, and college-wide event notifications.

The client application is built using modern Android engineering standards with Kotlin and Jetpack Compose following Clean Architecture and MVVM design patterns. It features local database persistence using Room DB (SQLite) and state management with Kotlin Coroutines and StateFlow. The backend is developed using Java and Spring Boot, providing RESTful APIs for authentication, session management, transaction handling, role-based access control, and service synchronization. PostgreSQL and Firebase Realtime DB / Cloud Firestore serve as the primary databases for storing user accounts, requests, menu items, orders, cloud telemetry, and event logs.

Security is implemented using Spring Security, Role-Based Access Control (RBAC), and JWT Authentication, ensuring strict access control across 8 distinct user roles (Student, Class Advisor, HOD, Principal, Warden, Gate Security, PA, and Admin). Gradle is used for client-side build automation, Maven/Gradle for backend dependency management, and Git with GitHub for version control and team collaboration.

The project provides practical experience in modern mobile application development, full-stack backend architecture, REST API design, role-based authorization systems, encrypted QR code generation and camera scanning, relational database management, and collaborative software engineering. It demonstrates how digital automation can transform campus administration and elevate the student experience in educational institutions.

---

### Objectives
- Develop a smart campus management and assistance platform for educational institutions.
- Unify student services, administrative approvals, gate security, campus commerce, and placement management into a single application.
- Implement automated multi-tier approval workflows for student outpasses and digital certificates.
- Integrate encrypted QR code generation and camera scanning for secure, tamper-proof gate verification.
- Develop a secure, scalable backend using Java, Spring Boot, PostgreSQL, and REST APIs.
- Build an intuitive, responsive mobile UI using Kotlin, Jetpack Compose, and Material 3 design system.
- Implement strict Role-Based Access Control (RBAC) across 8 user roles to guarantee data security and operational integrity.
- Demonstrate the practical implementation of full-stack software engineering, mobile UI design, and database systems in campus automation.

---

### Technologies Used
- **Client Programming Language**: Kotlin
- **Backend Programming Language**: Java
- **Client Framework**: Android SDK (Kotlin 1.9+, SDK 34+)
- **UI Engine**: Jetpack Compose, Material 3
- **Architecture Pattern**: MVVM & Clean Architecture
- **Local Database**: Room DB (SQLite) & SharedPreferences
- **State Management**: Kotlin StateFlow, Compose State, Coroutines
- **Backend Framework**: Spring Boot
- **Security**: Spring Security, JWT Authentication, Role-Based Access Control (RBAC)
- **Database**: Firebase Realtime DB / Cloud Firestore, PostgreSQL (Backend), Room SQLite (Local Client)
- **API Architecture**: RESTful APIs & JSON Data Exchange
- **Build Tools**: Gradle (Android), Maven / Gradle (Spring Boot)
- **Version Control**: Git & GitHub

---

### Key Features
- **Multi-Role Access Control**: Seamless access for 8 distinct roles (Student, Class Advisor, HOD, Principal, Warden, Security, PA, and Admin).
- **Digital Outpass Management**: Multi-level approval flow (Advisor ➔ HOD) with automated encrypted QR code generation upon approval.
- **Gate Security Verification**: Integrated camera and QR scanner interface for gate security to validate student exit/entry in real time.
- **Digital Certificate Engine**: Online applications for Bonafide, Conduct, Study, TC, and NOC certificates with multi-stage processing.
- **Smart Canteen Pre-Booking**: Categorized digital menu, shopping cart checkout, total cost calculation, and instant QR collection token generation.
- **Campus Store & Print Queue**: Document upload for custom print jobs (color/B&W, spiral binding, page selection) and online stationery ordering.
- **Training & Placement Cell (TPC) Hub**: Interactive drive listings, CTC package details, eligibility checks, and real-time interview stage tracking.
- **Academic Timetables**: Weekly departmental period schedules displaying timings, room numbers, subjects, and faculty names.
- **Notification & Event Bulletin Engine**: Central bulletin for campus fests, workshops, announcements, and request status alerts.
- **Hybrid Data Synchronization**: Local offline caching with Room DB and secure RESTful synchronization with PostgreSQL backend.

---

### Outcome
The project delivers a fully integrated, smart campus management and assistance platform for Narasaraopeta Engineering College, significantly enhancing operational efficiency, campus security, and administrative transparency. It eliminates paperwork delays by digitizing outpasses, certificates, canteen orders, print jobs, and placement tracking. The project demonstrates the successful integration of modern mobile development with Kotlin and Jetpack Compose, secure Spring Boot REST APIs, relational PostgreSQL databases, and QR code security mechanisms. Additionally, it strengthens the team's practical expertise in Java, Kotlin, Spring Boot, REST APIs, PostgreSQL, mobile app development, and collaborative software engineering.

---

<p align="center">
  <b>Prepared by RANBIDGE Solutions Private Limited</b><br>
  <i>Developers: R GOPINATH REDDY & V KONDA REDDY</i><br>
  For Narasaraopeta Engineering College
</p>
