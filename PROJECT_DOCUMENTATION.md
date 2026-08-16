# Campus Connect: Overall Application Documentation

> **Smart Campus Management & Assistance Application**  
> **Narasaraopeta Engineering College**

---

## 📋 Table of Contents
1. [Executive Summary](#1-executive-summary)
2. [Application Architecture](#2-application-architecture)
3. [User Roles & Permissions Matrix](#3-user-roles--permissions-matrix)
4. [Core Features & Modules](#4-core-features--modules)
   - [4.1 Authentication & Login System](#41-authentication--login-system)
   - [4.2 Digital Outpass Management & Gate Security](#42-digital-outpass-management--gate-security)
   - [4.3 Digital Certificate Request Engine](#43-digital-certificate-request-engine)
   - [4.4 Smart Canteen Pre-Booking](#44-smart-canteen-pre-booking)
   - [4.5 Campus Store & Custom Print Queue](#45-campus-store--custom-print-queue)
   - [4.6 Training & Placement Cell (TPC) Hub](#46-training--placement-cell-tpc-hub)
   - [4.7 Academic Timetable & Schedules](#47-academic-timetable--schedules)
   - [4.8 Event Bulletin & Notification Engine](#48-event-bulletin--notification-engine)
   - [4.9 Administrative & Staff Hubs](#49-administrative--staff-hubs)
5. [Database Schema & Data Entities](#5-database-schema--data-entities)
6. [UI/UX Design System](#6-uiux-design-system)
7. [Technical Stack](#7-technical-stack)
8. [Summary & Future Enhancements](#8-summary--future-enhancements)

---

## 1. Executive Summary

**Campus Connect** is a comprehensive, multi-role mobile and desktop campus management application engineered for **Narasaraopeta Engineering College**. The application unifies administrative operations, student services, gate security, campus commerce, academic timetables, placement management, and institutional communication into a single digital platform.

By replacing traditional paper workflows with automated multi-tier approval channels, encrypted QR code generation, real-time status tracking, and structured databases, **Campus Connect** dramatically improves operational efficiency and campus security.

---

## 2. Application Architecture

The application is structured using modern Android development practices, following the **Clean Architecture** pattern:

```mermaid
flowchart TD
    subgraph UI Layer (Jetpack Compose)
        A[MainActivity / AppNavHost]
        B[Screen Components]
        B1[LoginScreen]
        B2[DashboardScreen]
        B3[OutpassScreen]
        B4[CertificateScreen]
        B5[CanteenScreen]
        B6[PrintScreen]
        B7[TPCScreen]
        B8[TimeTableScreen]
        B9[AdminScreen & Staff Hubs]
    end

    subgraph State Management Layer
        C[PortalViewModel]
        D[StateFlow / MutableState]
    end

    subgraph Data & Repository Layer
        E[AppRepository]
        F[AppDao]
        G[(Room Local Database)]
    end

    subgraph External / Backend Integration
        H[Spring Boot REST APIs / PostgreSQL]
        I[Firebase Hub Integration]
    end

    A --> B
    B --> C
    C <--> D
    C <--> E
    E <--> F
    F <--> G
    E <--> H
    E <--> I
```

---

## 3. User Roles & Permissions Matrix

**Campus Connect** implements strict Role-Based Access Control (RBAC) across 8 user roles:

| User Role | Target Users | Key Capabilities & Access Rights |
| :--- | :--- | :--- |
| **STUDENT** | College Students | Submit outpasses, request certificates, pre-order canteen food, submit print jobs, order stationery, view timetables, apply for placement drives, track requests. |
| **CLASS_ADVISOR** | Faculty Advisors | Level-1 outpass approvals for assigned students, view advisor student list, review certificate requests, communicate via advisor chat. |
| **HOD** | Department Heads | Level-2 outpass approvals, department certificate reviews, send department-wide announcements, review outpass summary sheets. |
| **PRINCIPAL** | College Principal | Final certificate approvals, executive campus metrics dashboard, college-wide notification broadcasts, policy management. |
| **WARDEN** | Hostel Wardens | Hostel outpass oversight, resident verification, curfew tracking. |
| **SECURITY** | Gate Security Staff | Gate exit/entry verification via camera/built-in QR code scanner. Validates approved student outpasses. |
| **PA** | Principal Assistant | Processing and printing approved student certificates. |
| **ADMIN** | System Administrators | User creation, role assignment, mentor/advisor mapping, canteen menu setup, stationery stock control, account management. |

---

## 4. Core Features & Modules

### 4.1 Authentication & Login System
- Role-based login screen (`LoginScreen.kt`) with quick role switching for testing/demonstration.
- Secure session state persisted locally and synchronized with backend REST APIs.

### 4.2 Digital Outpass Management & Gate Security
- **Student Submission**: Specify departure date/time, reason, return time, and parent phone contact (`OutpassScreen.kt`).
- **Multi-Level Approval Flow**:
  1. **Pending Advisor**: Assigned Class Advisor receives request and approves/rejects.
  2. **Pending HOD**: HOD reviews and grants departmental permission.
  3. **Approved with QR**: Generates an encrypted QR code containing student details and approval signature.
  4. **Gate Security Verification**: Security scans the QR code at the gate; status updates to `COMPLETED`.

### 4.3 Digital Certificate Request Engine
- **Applications Offered**: Bonafide Certificate, Study Certificate, Conduct Certificate, Transfer Certificate (TC), Internship NOC, Fee Structure Letter.
- **Workflow**: Student applies ➔ HOD approves ➔ Principal authorizes ➔ PA desk prints and marks ready for pickup.

### 4.4 Smart Canteen Pre-Booking
- **Categorized Menu**: Breakfast, Lunch, Snacks, Beverages, Dinner (`CanteenScreen.kt`).
- **Cart & Order Checkout**: Online item selection, total cost computation, and instant booking.
- **QR Collection Token**: Generates a unique QR token to show at the canteen counter for fast pickup.

### 4.5 Campus Store & Custom Print Queue
- **Stationery Store**: Purchase pens, notebooks, lab manuals, drawing books, and folders (`StationeryScreen.kt`).
- **Custom Print Request Hub**:
  - Upload PDF/document files (`PrintScreen.kt`).
  - Customize print parameters: page count, color vs. black-and-white, copy type (Xerox, Project Report), binding (Spiral vs. Normal).
  - Automated pricing calculation and queue tracking (`Queued` ➔ `Printing` ➔ `Ready`).

### 4.6 Training & Placement Cell (TPC) Hub
- **Placement Drive Listing**: View recruiting companies, job roles, CTC packages, application deadlines, and eligibility criteria (`TPCScreen.kt`).
- **Application Tracking**: Apply for drives and monitor interview round progress.

### 4.7 Academic Timetable & Schedules
- Interactive weekly schedule per department and section (`TimeTableScreen.kt`).
- Displays period timings, subject names, room numbers, and faculty details.

### 4.8 Event Bulletin & Notification Engine
- College notification center with unread badges and categorization (`General`, `Outpass`, `Certificate`, `Canteen`, `Store`, `Print`).
- Central event bulletin board for technical symposiums, fests, workshops, and sports tournaments.

### 4.9 Administrative & Staff Hubs
- **AdminScreen.kt**: Comprehensive management portal for user accounts, stock control, and role assignments.
- **StationeryPrintingStaffHub.kt**: Dedicated dashboard for printing and stationery staff to fulfill incoming print jobs and store orders.
- **FirebaseHubScreen.kt**: Integration hub for cloud messaging and sync telemetry.

---

## 5. Database Schema & Data Entities

The application utilizes local Room DB entities (`Entities.kt`) mapped to backend PostgreSQL tables:

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                             DATABASE ENTITIES                               │
├───────────────────┬─────────────────────────────────────────────────────────┤
│ User              │ userId, name, rollNumber, department, email, phone,     │
│                   │ parentContact, role, assignedMentorId, assignedAdvisorId│
├───────────────────┼─────────────────────────────────────────────────────────┤
│ OutpassRequest    │ id, studentId, studentName, rollNumber, department,     │
│                   │ dateTime, reason, expectedReturnTime, status, qrText    │
├───────────────────┼─────────────────────────────────────────────────────────┤
│ CertificateRequest│ id, studentId, studentName, rollNumber, department,     │
│                   │ certificateType, details, status, timestamp             │
├───────────────────┼─────────────────────────────────────────────────────────┤
│ CanteenBooking    │ id, studentId, studentName, itemsJson, totalCost,       │
│                   │ status, qrToken, timestamp                              │
├───────────────────┼─────────────────────────────────────────────────────────┤
│ PrintRequest      │ id, studentId, studentName, fileName, pagesCount,       │
│                   │ printType, copyType, bindingType, totalCost, status     │
├───────────────────┼─────────────────────────────────────────────────────────┤
│ StationeryRequest │ id, studentId, studentName, itemName, quantity,        │
│                   │ totalCost, status, timestamp                            │
├───────────────────┼─────────────────────────────────────────────────────────┤
│ CollegeNotification│ id, targetStudentId, title, content, category, isRead  │
├───────────────────┼─────────────────────────────────────────────────────────┤
│ CollegeEvent      │ id, title, description, date, time, venue, organizerRole │
└───────────────────┴─────────────────────────────────────────────────────────┘
```

---

## 6. UI/UX Design System

- **Framework**: Jetpack Compose using Material 3 design guidelines.
- **Visual Palette**: Premium dark-mode accents, glassmorphic cards, vibrant category badges, and smooth state transitions.
- **Accessibility & Feedback**: In-app banner alerts (`SuccessAlertBanner.kt`), modal dialogs, and instant state feedback across user actions.

---

## 7. Technical Stack

- **Client Platform**: Android (Kotlin 1.9+, SDK 34+)
- **UI Engine**: Jetpack Compose, Material 3
- **Architecture**: MVVM (Model-View-ViewModel), Clean Architecture
- **State Flow**: Kotlin `StateFlow`, Compose `State`, Coroutines
- **Persistence**: Room DB (SQLite), `SharedPreferences`
- **Backend / Cloud Databases**: Java Spring Boot, REST APIs, PostgreSQL, Firebase Realtime DB / Cloud Firestore
- **Build System**: Gradle (`build.gradle.kts`)

---

## 8. Summary & Future Enhancements

**Campus Connect** provides an all-in-one digital infrastructure for **Narasaraopeta Engineering College**. By integrating mobile access, automated approvals, digital commerce, placement management, and security gate passes, it streamlines daily campus activities for students and administrators alike.

### Future Roadmap:
- **Biometric Integration**: Fingerprint/Face ID gate verification.
- **AI Voice Bot Pipeline**: Direct speech interaction powered by Pipecat STT/TTS engine.
- **Real-Time GPS Transport Tracking**: Live location monitoring for campus buses.

---

<p align="center">
  <b>Prepared by RANBIDGE Solutions Private Limited</b><br>
  <i>Developers: R GOPINATH REDDY & V KONDA REDDY</i><br>
  For Narasaraopeta Engineering College
</p>
