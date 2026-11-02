# 📘 CAMPUS CONNECT: COMPLETE APPLICATION MANUAL & USER HANDBOOK
**Smart Campus Management & Assistance Application**  
**Narasaraopeta Engineering College (NEC)**  
*Prepared by RANBIDGE Solutions Private Limited*  
*Project Developers: R GOPINATH REDDY & V KONDA REDDY*  

---

## 📑 TABLE OF CONTENTS
1. [About the Application](#1-about-the-application)
2. [How to Open and Run the Application](#2-how-to-open-and-run-the-application)
   - 2.1 [System Requirements](#21-system-requirements)
   - 2.2 [Step-by-Step Setup in Android Studio](#22-step-by-step-setup-in-android-studio)
   - 2.3 [Running on Phone or Emulator](#23-running-on-phone-or-emulator)
   - 2.4 [Building the APK File via Terminal](#24-building-the-apk-file-via-terminal)
   - 2.5 [Default Login Accounts & Passwords](#25-default-login-accounts--passwords)
3. [User Roles and Permissions](#3-user-roles-and-permissions)
4. [Screen-by-Screen User Manual (With Screenshot Placeholders)](#4-screen-by-screen-user-manual)
   - 4.1 [Screen 1: Login & Registration Screen](#41-screen-1-login--registration-screen)
   - 4.2 [Screen 2: Student Main Dashboard](#42-screen-2-student-main-dashboard)
   - 4.3 [Screen 3: User Profile & Edit Dialog](#43-screen-3-user-profile--edit-dialog)
   - 4.4 [Screen 4: Announcements & Live Alert Notifications](#44-screen-4-announcements--live-alert-notifications)
   - 4.5 [Screen 5: Digital Outpass Clearance & Gate Pass](#45-screen-5-digital-outpass-clearance--gate-pass)
   - 4.6 [Screen 6: Digital Certificate Request Hub](#46-screen-6-digital-certificate-request-hub)
   - 4.7 [Screen 7: Smart Canteen Meal Booking & QR Tokens](#47-screen-7-smart-canteen-meal-booking--qr-tokens)
   - 4.8 [Screen 8: Academic Stationery Store](#48-screen-8-academic-stationery-store)
   - 4.9 [Screen 9: Document Printout Center](#49-screen-9-document-printout-center)
   - 4.10 [Screen 10: Consolidated Request Timeline (Track Requests)](#410-screen-10-consolidated-request-timeline-track-requests)
   - 4.11 [Screen 11: Academic Timetable & Attendance System](#411-screen-11-academic-timetable--attendance-system)
   - 4.12 [Screen 12: Training & Placement Cell (TPC) Hub](#412-screen-12-training--placement-cell-tpc-hub)
   - 4.13 [Screen 13: Staff, Faculty & HOD Approval Console](#413-screen-13-staff-faculty--hod-approval-console)
   - 4.14 [Screen 14: Gate Security QR Verification Scanner](#414-screen-14-gate-security-qr-verification-scanner)
   - 4.15 [Screen 15: Canteen Manager Orders & Token Redemption](#415-screen-15-canteen-manager-orders--token-redemption)
   - 4.16 [Screen 16: Stationery & Printing Staff Hub](#416-screen-16-stationery--printing-staff-hub)
   - 4.17 [Screen 17: Super Administrator Master Portal](#417-screen-17-super-administrator-master-portal)
   - 4.18 [Screen 18: Firebase Cloud Sync & Database Telemetry](#418-screen-18-firebase-cloud-sync--database-telemetry)
5. [End-to-End Workflow Examples](#5-end-to-end-workflow-examples)
6. [Troubleshooting & Frequently Asked Questions (FAQ)](#6-troubleshooting--frequently-asked-questions-faq)
7. [Conclusion & Project Summary](#7-conclusion--project-summary)

---

## 1. ABOUT THE APPLICATION

**Campus Connect** is an all-in-one digital mobile and tablet application created for **Narasaraopeta Engineering College (NEC)**. The main goal of this application is to make college life smooth, fast, and completely paperless.

### What Problems Does It Solve?
* **No More Paper Outpasses:** Students previously had to walk between multiple faculty cabins and HOD offices to collect physical signatures on outpass slips. With Campus Connect, requests are approved digitally on the phone in seconds.
* **No Long Canteen Queues:** Instead of standing in crowded lines during short 15-minute breaks, students pre-book food and receive a unique QR token to pick up their meals instantly.
* **Easy Certificate Applications:** Official letters like Bonafide certificates, Study certificates, and NOCs are requested in three clicks and tracked until ready for pickup.
* **Smart Print Queue:** Students upload PDFs directly from their phones, customize page settings (color, black-and-white, spiral binding), and pick them up at the stationery counter without waiting for pendrives.
* **Single Gate Security Scan:** Security personnel at the college gate scan the student's digital QR pass on their phone to verify approved campus departures instantly.

---

## 2. HOW TO OPEN AND RUN THE APPLICATION

Follow these simple, step-by-step instructions to open, build, and run the Campus Connect app on your computer.

### 2.1 System Requirements
To run this application, make sure you have:
1. **Operating System:** Windows 10/11, macOS, or Linux.
2. **Java Development Kit (JDK):** JDK 17 or JDK 21 installed.
3. **Android Studio:** Android Studio Hedgehog, Iguana, Jellyfish, Koala, Ladybug, or higher.
4. **Android SDK:** Android SDK Platform 34 or 35 installed.
5. **A Device to Test:**
   * An **Android Virtual Device (AVD / Emulator)** inside Android Studio, OR
   * A **Physical Android Smartphone** connected via USB cable with **USB Debugging** enabled in Developer Options.

---

### 2.2 Step-by-Step Setup in Android Studio

```
[ Step 1: Open Folder ]  --->  [ Step 2: Gradle Sync ]  --->  [ Step 3: Select Device ]  --->  [ Step 4: Click Run (▶) ]
```

1. **Open Android Studio.**
2. On the welcome screen, click **Open** (or go to `File > Open...` if Android Studio is already open).
3. Browse to the folder:
   ```
   C:\Users\GOPINATHREDDY R\Desktop\Campus-Connect
   ```
4. Click **OK**.
5. Android Studio will open the project and automatically start the **Gradle Sync** process.
6. Wait 1 to 3 minutes for Android Studio to index the project files and download the required libraries. Look at the bottom status bar: when it says `Gradle sync finished`, you are ready to proceed!

---

### 2.3 Running on Phone or Emulator

1. **Set Up an Emulator (if not using a phone):**
   * In Android Studio, open the **Device Manager** (top-right toolbar icon with a small phone).
   * Click **Create Device**.
   * Choose **Pixel 7** or **Pixel 8** and click **Next**.
   * Download System Image **API 34** or **API 35** (VanillaIceCream or UpsideDownCake).
   * Click **Finish**.
2. **Select Your Device:**
   * In the top toolbar of Android Studio, locate the device dropdown menu.
   * Select your emulator name or your connected physical smartphone.
3. **Launch the Application:**
   * Click the green **Run button (▶)** or press `Shift + F10` on your keyboard.
   * The application will build and launch automatically on your screen!

---

### 2.4 Building the APK File via Terminal

If you want to create an `.apk` file that you can send to other phones or install directly, run this command in your PowerShell or Command Prompt inside the project folder:

```powershell
# Navigate to the project directory
cd "C:\Users\GOPINATHREDDY R\Desktop\Campus-Connect"

# Build the Debug APK
.\gradlew.bat assembleDebug
```

Once the build finishes successfully, your APK file will be ready at:
```
app\build\outputs\apk\debug\app-debug.apk
```
You can copy this `.apk` file to any Android phone and install it immediately.

---

### 2.5 Default Login Accounts & Passwords

The application includes pre-loaded test accounts for every single campus role. You can log in using either the **User ID / Roll Number** or the **Official Email**. The default password for all pre-seeded accounts is `password` or the user ID itself.

| Role | User ID / Roll No | Official Email | Default Password | What this role tests |
| :--- | :--- | :--- | :--- | :--- |
| **Student** | `23CS101` | `alex.rivera@nrtec.in` | `password` | Submitting outpasses, certificates, canteen food, print jobs |
| **Student 2** | `23EC204` | `emily.watson@nrtec.in` | `password` | Student from ECE department |
| **Class Advisor** | `advisor` | `advisor@technoelite.com` | `password` | Level-1 outpass approvals for assigned students |
| **Academic Mentor** | `mentor` | `mentor@technoelite.com` | `password` | Student mentorship, request monitoring |
| **HOD (Head of Dept)**| `hod` | `hod@hod.com` | `password` | Level-2 departmental approvals, timetables |
| **Principal** | `principal` | `principal@principal.com` | `password` | Final certificate signoffs, staff overview |
| **Hostel Warden** | `warden` | `suresh.kumar@college.edu` | `password` | Hostel resident outpass verification |
| **Gate Security** | `security` | `security@necsecurity.in` | `password` | Gate camera QR scanner for student clearance |
| **Canteen Manager** | `canteen` | `canteen@neccanteen.com` | `password` | Live canteen orders, food token redemption |
| **Store & Printing** | `store` | `store@necstationary.in` | `password` | Print job queue fulfillment, stationery orders |
| **Personal Assistant**| `pa` | `pa@pa.com` | `password` | Official certificate printing and dispatch |
| **Administrator** | `admin` | `admin@ranbidge.com` | `password` | Master console, CSV student import, staff allocations |

---

## 3. USER ROLES AND PERMISSIONS

Campus Connect uses **Role-Based Access Control (RBAC)**. When a user logs in, the app automatically customizes what they can see and do:

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                             ROLE ACCESS SUMMARY                             │
├────────────────────┬────────────────────────────────────────────────────────┤
│ STUDENT            │ Can create outpass, certificate, food, and print jobs. │
│                    │ Read-only view of timetables and placement drives.     │
├────────────────────┼────────────────────────────────────────────────────────┤
│ CLASS ADVISOR      │ Approves student outpasses at Level 1.                 │
│                    │ Reviews certificate requests of assigned class.        │
├────────────────────┼────────────────────────────────────────────────────────┤
│ HEAD OF DEPT (HOD) │ Approves outpasses at Level 2 (Final departure grant). │
│                    │ Posts department announcements and edits timetables.   │
├────────────────────┼────────────────────────────────────────────────────────┤
│ PRINCIPAL          │ Authorizes official college certificates.              │
│                    │ Broadcasts campus-wide alerts and views analytics.     │
├────────────────────┼────────────────────────────────────────────────────────┤
│ GATE SECURITY      │ Scans student QR passes at the gate.                   │
│                    │ Verifies time of exit/entry and marks pass COMPLETED.  │
├────────────────────┼────────────────────────────────────────────────────────┤
│ CANTEEN STAFF      │ Manages daily menu, checks incoming meal tokens.       │
│                    │ Marks food as served and tallies daily revenue.        │
├────────────────────┼────────────────────────────────────────────────────────┤
│ STORE & PRINT DESK │ Downloads student print files, sets job to Printing.   │
│                    │ Manages notebook/pen stocks and marks orders ready.    │
├────────────────────┼────────────────────────────────────────────────────────┤
│ SYSTEM ADMIN       │ Controls all user accounts, imports students via CSV,  │
│                    │ Maps mentors to classes, views full system audit logs. │
└────────────────────┴────────────────────────────────────────────────────────┘
```

---

## 4. SCREEN-BY-SCREEN USER MANUAL

This section details every single screen in the Campus Connect application. It explains the visual layout, every button's function, how to use the screen step-by-step, and provides a designated placeholder where you can attach your screenshot.

---

### 4.1 Screen 1: Login & Registration Screen

> 📸 **SCREENSHOT PLACEHOLDER #1: Login & Registration Screen**
> ```
> +-------------------------------------------------------+
> |   [ NEC College Logo: img_nec_logo_new.png ]          |
> |   CAMPUS CONNECT                                      |
> |   Unified Services & Approvals Portal                 |
> |                                                       |
> |   [ User ID / Email Input Field                     ] |
> |   [ Password Input Field                          👁 ] |
> |                                                       |
> |   [           AUTHENTICATE (Button)                 ] |
> |                                                       |
> |   New to the portal? [ Register Account ]             |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/01_login_screen.png`*  
> *Caption: Campus Connect authentication portal showing college branding, input fields, and register account options.*

#### Purpose:
The gateway to the entire platform. Users enter their ID or official institutional email to access their customized role workspace.

#### Visual Elements & Buttons:
* **College Crest Logo:** Displays the official Narasaraopeta Engineering College emblem.
* **User ID / Email Input Box:** Accepts Roll numbers (e.g. `23CS101`), Staff IDs (e.g. `advisor`), or emails (e.g. `alex.rivera@nrtec.in`).
* **Password Box with Eye Icon:** Secure text field with a toggle to hide or view the password.
* **Authenticate Button:** Validates credentials and logs the user in.
* **Register Account Tab:** Opens the registration form for new students or faculty members.
* **Auto-Department Selector (Registration Mode):** When a student types their roll number (e.g. `23CS105`), the app automatically detects their department as *Computer Science*.

#### How to Use:
1. Enter your **ID** or **Email** in the first field.
2. Enter your **Password** in the second field.
3. Tap **Authenticate**.
4. If you are a new student, click **Register Account**, fill in your full name, roll number, official `@nrtec.in` email, choose a password, and tap **Create Account & Log In**.

---

### 4.2 Screen 2: Student Main Dashboard

> 📸 **SCREENSHOT PLACEHOLDER #2: Student Main Dashboard**
> ```
> +-------------------------------------------------------+
> | Welcome back,                                         |
> | Alex Rivera                                           |
> | 23CS101 • Computer Science                            |
> | [ Mentor: Prof. Sarah ]  [ Class Teacher: Dr. Finch ] |
> |                                                       |
> | +---------------------------------------------------+ |
> | | [ Campus Banner Image ]                           | |
> | | NEC Narasaraopeta Portal                          | |
> | +---------------------------------------------------+ |
> |                                                       |
> | [ Active Canteen Token: #TKN-8421 - Veg Meals (Full)] |
> |                                                       |
> | Services Directory:                                   |
> | [ Outpass Clearance ]        [ Certificate Hub ]      |
> | [ Stationery Store ]         [ Printout Center ]      |
> | [ Canteen Token ]            [ Track Requests ]       |
> | [ Help Desk ]                [ Time Table ]           |
> | [ Campus Events ]                                     |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/02_student_dashboard.png`*  
> *Caption: Student home screen showing personalized greeting, assigned advisor badges, hero banner, active food token, and service directory tiles.*

#### Purpose:
The main home screen for students. Provides a high-level view of active requests, quick access to all college services, and displays the student's assigned Mentor and Class Advisor.

#### Visual Elements & Buttons:
* **Welcome Card:** Shows the student's name, roll number, department, and direct chips for their assigned **Academic Mentor** and **Class Teacher**.
* **Campus Cover Banner:** High-definition campus imagery.
* **Active Canteen Token Widget:** Displays if the student has an active meal token ready to be picked up.
* **Services Directory Grid:**
  * **Outpass Clearance:** Apply for leave permission.
  * **Certificate Hub:** Request bonafide, study, or transfer certificates.
  * **Stationery Store:** Order notebooks, lab manuals, and pens.
  * **Printout Center:** Send PDF documents to the college printer.
  * **Canteen Token:** Pre-book breakfast, lunch, or snacks.
  * **Track Requests:** View the consolidated live timeline of all submitted requests.
  * **Time Table:** View today's schedule and attendance percentage.
  * **Help Desk & Campus Events:** Contact helpline numbers and browse upcoming seminars and fests.

#### How to Use:
1. Glance at the top banner to see assigned mentor details.
2. If you have an active meal token, tap the token card to display its QR code for pickup.
3. Tap any service tile (e.g., **Outpass Clearance**) to jump directly into that module.

---

### 4.3 Screen 3: User Profile & Edit Dialog

> 📸 **SCREENSHOT PLACEHOLDER #3: User Profile & Edit Modal**
> ```
> +-------------------------------------------------------+
> |               Campus User Profile            [✏️] [🚪] |
> |                        🎓                             |
> |                   Alex Rivera                         |
> |         Authentication Level: STUDENT                 |
> | ----------------------------------------------------- |
> | Student Roll ID:     23CS101                          |
> | College Department:  Computer Science                 |
> | Official Email:      alex.rivera@nrtec.in             |
> | Phone Number:        +91 9441234567                   |
> | Parent Contact:      +91 9885123456                   |
> | ----------------------------------------------------- |
> |                 [   Finished   ]                      |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/03_profile_dialog.png`*  
> *Caption: User profile modal displaying student particulars, parental emergency contact, and inline edit mode.*

#### Purpose:
Allows users to verify their official institutional records and update personal contact information or emergency parent phone numbers.

#### Visual Elements & Buttons:
* **Profile Avatar:** Student badge emblem.
* **Detail Rows:** Shows Roll Number, Department, Institutional Email, Personal Mobile Number, and Parent Contact Number.
* **Edit Icon (✏️):** Converts the view into editable text boxes to update phone, email, or parent contact info.
* **Save / Discard Buttons:** Persists changes to the local Room database and Cloud Firestore.
* **Logout Icon (🚪):** Logs out of the current session safely.

#### How to Use:
1. Tap the **Profile Icon** in the top-left corner of the top navigation bar.
2. Tap the **Edit (✏️)** button if your phone number or parent contact has changed.
3. Type the updated contact information and tap **Save**.

---

### 4.4 Screen 4: Announcements & Live Alert Notifications

> 📸 **SCREENSHOT PLACEHOLDER #4: Announcements & Notifications**
> ```
> +-------------------------------------------------------+
> |   Announcements                         [Mark Read]   |
> |                                                       |
> |   +-------------------------------------------------+ |
> |   | Outpass Approved!                     [Outpass] | |
> |   | Your outpass to visit home has been approved by | |
> |   | HOD. Encrypted gate QR code is generated.       | |
> |   +-------------------------------------------------+ |
> |   +-------------------------------------------------+ |
> |   | Print Job Completed                     [Print] | |
> |   | Your document 'AI_Project.pdf' is ready for     | |
> |   | collection at the stationery store counter.     | |
> |   +-------------------------------------------------+ |
> |                                                       |
> |                     [ Dismiss ]                       |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/04_notifications_modal.png`*  
> *Caption: College notification center showing categorized unread alerts with category tags.*

#### Purpose:
Keeps students and faculty immediately informed about approval milestones, print status updates, and college announcements.

#### Visual Elements & Buttons:
* **Top Bell Icon with Unread Badge:** Highlights how many unread notices you have.
* **Heads-Up Banner:** Slides in from the top of the screen when an outpass or certificate approval happens in real time.
* **Mark Read Button:** Clears all unread badges in one click.
* **Category Chips:** Color-coded badges indicating `Outpass`, `Certificate`, `Canteen`, or `Print`.

#### How to Use:
1. Tap the **Bell Icon (🔔)** in the top app bar.
2. Read incoming notices.
3. Tap on any notification to navigate directly to that specific request's timeline.

---

### 4.5 Screen 5: Digital Outpass Clearance & Gate Pass

> 📸 **SCREENSHOT PLACEHOLDER #5: Digital Outpass Clearance & QR Gate Pass**
> ```
> +-------------------------------------------------------+
> |   Outpass Clearance                     [+ New Request]|
> |   Request campus exit permission                      |
> | ----------------------------------------------------- |
> |                                                       |
> |   +-------------------------------------------------+ |
> |   | OUTPASS TICKET: #OP-9042                        | |
> |   | Student: Alex Rivera (23CS101)                  | |
> |   | Status:  APPROVED BY HOD                        | |
> |   | Reason:  Family emergency / Weekend visit       | |
> |   | Departure: Today, 04:00 PM                      | |
> |   | Expected Return: Today, 08:30 PM                | |
> |   |                                                 | |
> |   |            [ ENCRYPTED QR CODE ]                | |
> |   |          Scan at Gate Security Desk             | |
> |   |                                                 | |
> |   |   [ Download PDF Slip ]   [ Print Gate Pass ]   | |
> |   +-------------------------------------------------+ |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/05_outpass_screen.png`*  
> *Caption: Digital outpass clearance screen with approved gate pass showing encrypted QR code and parent contact verification.*

#### Purpose:
Replaces physical outpass paperwork with an automated digital approval channel (Advisor ➔ HOD ➔ Gate Security).

#### Visual Elements & Buttons:
* **New Request Button:** Opens the outpass application form.
* **Application Form Fields:**
  * Expected Departure Date and Time (e.g. `Today, 04:00 PM`).
  * Reason for exit (e.g. `Doctor Consultation`, `Weekend Home Visit`).
  * Expected Return Time (e.g. `Today, 08:30 PM`).
  * Parent Phone Contact (auto-filled from student profile).
* **Live Status Badge:** Displays `PENDING_ADVISOR`, `PENDING_HOD`, `APPROVED`, or `REJECTED`.
* **Encrypted QR Code:** Automatically rendered when both Advisor and HOD approve the outpass. Gate security scans this code at the gate.
* **Download PDF Slip Button:** Simulates generating a formal PDF exit voucher.

#### How to Use:
1. Tap **+ New Request**.
2. Confirm your departure time, reason, and return time.
3. Tap **Submit Outpass Request**.
4. Once your Class Advisor and HOD approve, open this screen to show your **Encrypted QR Code** to the gate security guard when leaving campus.

---

### 4.6 Screen 6: Digital Certificate Request Hub

> 📸 **SCREENSHOT PLACEHOLDER #6: Digital Certificate Services**
> ```
> +-------------------------------------------------------+
> |   Certificate Services                                |
> |   Request official transcripts & letters online       |
> | ----------------------------------------------------- |
> |   [ Select & Apply (Tab) ]   [ My Certificates (Tab) ]|
> |                                                       |
> |   Available Certificates:                             |
> |   +-------------------------------------------------+ |
> |   | 📜 Bonafide Certificate                         | |
> |   | Proof of active enrollment for bus pass & bank  | |
> |   | [ Apply Now ]                                   | |
> |   +-------------------------------------------------+ |
> |   | 📜 Study & Conduct Certificate                  | |
> |   | Official academic performance & character cert  | |
> |   | [ Apply Now ]                                   | |
> |   +-------------------------------------------------+ |
> |   | 📜 Internship No Objection Certificate (NOC)    | |
> |   | Required for off-campus summer internships      | |
> |   | [ Apply Now ]                                   | |
> |   +-------------------------------------------------+ |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/06_certificate_screen.png`*  
> *Caption: Certificate services catalog with two-tab layout for applying and monitoring approval stages.*

#### Purpose:
Allows students to apply for official administrative college certificates without visiting administrative offices.

#### Visual Elements & Buttons:
* **Tabs:**
  * **Select & Apply:** Browse certificate types (Bonafide, Study, Transfer Certificate, Conduct Certificate, Internship NOC, Fee Structure).
  * **My Certificates:** Track previous applications.
* **Certificate Application Form:**
  * Selected Certificate Type.
  * Academic Year selection (e.g. `2025-2026`).
  * Purpose of Certificate (e.g. `Needed for Passport application`).
* **Multi-Tier Approval Pipeline Tracker:** Visual step tracker displaying:
  `Submitted` ➔ `Advisor Verification` ➔ `HOD Endorsement` ➔ `Principal Authorization` ➔ `PA Office Printing` ➔ `Ready for Collection`.

#### How to Use:
1. Select the certificate you require from the list.
2. Enter the academic year and specific purpose.
3. Tap **Submit Application**.
4. Switch to the **My Certificates** tab to view your approval progress.

---

### 4.7 Screen 7: Smart Canteen Meal Booking & QR Tokens

> 📸 **SCREENSHOT PLACEHOLDER #7: Smart Canteen Meal Booking & Token**
> ```
> +-------------------------------------------------------+
> |   Canteen Token Booking                               |
> |   Skip the queue! Pre-book meals and generate tokens  |
> | ----------------------------------------------------- |
> |   [ Pre-book Meal (Tab) ]     [ Food Tokens (1) ]     |
> |                                                       |
> |   [ All ] [ Breakfast ] [ Lunch ] [ Snacks ] [ Drinks]|
> |                                                       |
> |   +-------------------------------------------------+ |
> |   | Masala Dosa                           ₹40.00    | |
> |   | Crispy golden dosa served with sambar & chutney | |
> |   | [ - ]  1  [ + ]                     [ Add Cart ]| |
> |   +-------------------------------------------------+ |
> |   | Filter Coffee                         ₹12.00    | |
> |   | Authentic South Indian hot filter coffee        | |
> |   | [ Add Cart ]                                    | |
> |   +-------------------------------------------------+ |
> |                                                       |
> |   [ View Cart (2 Items) - Total: ₹52.00  Checkout > ] |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/07_canteen_screen.png`*  
> *Caption: Canteen ordering interface with category filter chips, item counters, and checkout bar.*

#### Purpose:
Enables students and faculty to order canteen food in advance, calculate total costs dynamically, and collect meals using digital QR tokens.

#### Visual Elements & Buttons:
* **Category Filters:** Filter food by `All`, `Breakfast`, `Lunch`, `Snacks`, `Dinner`, or `Beverages`.
* **Food Cards:** Shows item title, price in Indian Rupees (₹), category, and quantity increment/decrement buttons (`+` and `-`).
* **Floating Cart Bar:** Displays total selected items and cumulative cost.
* **Checkout Sheet:** Confirms items, payment method, and generates an instant food collection token.
* **Food Tokens Tab:** Displays active tokens with large, easy-to-scan QR codes, order IDs, and status (`BOOKED`, `SERVED`, or `CANCELLED`).

#### How to Use:
1. Tap on your desired category (e.g. *Breakfast*).
2. Tap **[ + ]** to add items to your cart.
3. Tap the bottom **Checkout** bar.
4. Tap **Confirm & Generate Food Token**.
5. Go to the canteen pickup counter and show your **Food Token QR** to receive your meal without waiting in the payment queue.

---

### 4.8 Screen 8: Academic Stationery Store

> 📸 **SCREENSHOT PLACEHOLDER #8: Academic Stationery Store**
> ```
> +-------------------------------------------------------+
> |   Academic Stationery Store                           |
> |   Pre-order textbooks, records and writing materials  |
> | ----------------------------------------------------- |
> |   [ Item Catalog (Tab) ]      [ Pre-Orders (1) ]      |
> |                                                       |
> |   +-------------------------------------------------+ |
> |   | Standard Gel Pens (Blue/Black)                  | |
> |   | Category: Writing                               | |
> |   | [ ₹15.00 ]   [ 50 In Stock ]            [Order] | |
> |   +-------------------------------------------------+ |
> |   | Chemistry/Physics Record Book                   | |
> |   | Category: Notebooks                             | |
> |   | [ ₹65.00 ]   [ 25 In Stock ]            [Order] | |
> |   +-------------------------------------------------+ |
> |   | Engineers Drawing A2 Paper                      | |
> |   | Category: Laboratory                            | |
> |   | [ ₹12.00 ]   [ 100 In Stock ]           [Order] | |
> |   +-------------------------------------------------+ |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/08_stationery_screen.png`*  
> *Caption: Academic stationery store catalog displaying real-time stock levels and instant order buttons.*

#### Purpose:
Provides students with an online college book-depot catalog to purchase lab record books, drawing sheets, notebooks, and writing materials.

#### Visual Elements & Buttons:
* **Item Catalog Tab:** Lists all stationery products with real-time stock quantities.
* **Stock Badge:** Color-coded green (`In Stock`) or red (`Out of Stock`).
* **Quantity Selector Dialog:** Choose how many units you want to purchase.
* **Pre-Orders Tab:** Displays your ordered stationery, total amount due, and pickup readiness status.

#### How to Use:
1. Browse the items in the catalog.
2. Tap **Order** next to your required item (e.g. *Physics Record Book*).
3. Select the quantity and tap **Confirm Pre-Order**.
4. Collect the items from the college stationery counter by showing your order ID.

---

### 4.9 Screen 9: Document Printout Center

> 📸 **SCREENSHOT PLACEHOLDER #9: Document Printout Center & Preview**
> ```
> +-------------------------------------------------------+
> |   Printout & Resource Center            [Upload PDF]  |
> |   Upload college assignments & project files to print |
> |   [🟢 Firebase DB Connected]                          |
> | ----------------------------------------------------- |
> |                                                       |
> |   DOCUMENT PRINT CONFIGURATION:                       |
> |   File Name:   Data_Structures_Lab_Report.pdf         |
> |   Total Pages: [ 15 ]                                 |
> |                                                       |
> |   Print Color:    (o) Black & White   ( ) Color       |
> |   Copy Type:      (o) Normal Print    ( ) Report      |
> |   Binding Type:   ( ) None  (o) Spiral Binding (+₹40) |
> |                                                       |
> |   Estimated Total Cost: ₹70.00                        |
> |                                                       |
> |   [ Preview Document Margins ]   [ Submit Print Job ] |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/09_print_screen.png`*  
> *Caption: Printout center showing file configuration, color/binding options, live price calculation, and document preview.*

#### Purpose:
Eliminates pendrives and viruses in the college xerox shop. Students upload PDF files directly from their smartphones, customize paper preferences, and send jobs to the printing queue.

#### Visual Elements & Buttons:
* **Upload PDF Button:** Simulates document selection or integrates device file picking.
* **Page Count Box:** Specify how many pages need to be printed.
* **Color Selection Chips:** Choose between **Black & White** (₹2/page) or **Color** (₹10/page).
* **Copy Type:** Normal printout or Project Report format.
* **Binding Selector:** Add **Spiral Binding** (+₹40) or select None.
* **Live Price Calculator:** Instantly calculates `(Pages × Rate) + Binding Cost`.
* **Preview Document Margins Dialog:** Lets students inspect simulated margins and page orientation before spending money.
* **Job Status Badge:** Tracks whether the job is `QUEUED`, `PRINTING`, `READY_FOR_PICKUP`, or `COMPLETED`.

#### How to Use:
1. Tap **Upload PDF**.
2. Enter your document name and page count.
3. Select your desired color (B&W or Color) and binding style.
4. Tap **Preview Document Margins** to verify appearance.
5. Tap **Submit Print Job**. Visit the stationery desk when status changes to **READY**.

---

### 4.10 Screen 10: Consolidated Request Timeline (Track Requests)

> 📸 **SCREENSHOT PLACEHOLDER #10: Consolidated Request Timeline**
> ```
> +-------------------------------------------------------+
> |   Track Campus Requests                               |
> |   Consolidated tracking list of your papers & orders  |
> | ----------------------------------------------------- |
> |   [ All ]  [ Outpasses ]  [ Certificates ]  [ Store ] |
> |                                                       |
> |   +-------------------------------------------------+ |
> |   | 🏃 Outpass Clearance: OP-9042         [APPROVED]| |
> |   | Reason: Family emergency / Weekend visit        | |
> |   | Departure: Today, 04:00 PM                      | |
> |   +-------------------------------------------------+ |
> |   | 📜 Certificate Applied: Bonafide     [PROCESSING]|
> |   | Purpose: Necessary for scholarship renewal     | |
> |   | Academic Year: 2025-2026                        | |
> |   +-------------------------------------------------+ |
> |   | 🍔 Canteen Token: #TKN-8421            [BOOKED] | |
> |   | 1x Veg Meals (Full) • Total: ₹80.00             | |
> |   +-------------------------------------------------+ |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/10_track_requests_screen.png`*  
> *Caption: Unified timeline aggregating all student outpass, certificate, print, canteen, and store orders.*

#### Purpose:
A single master timeline where students can track every transaction, application, and order they have ever submitted in chronological order.

#### Visual Elements & Buttons:
* **Filter Chips:** Filter activities by `All`, `Outpasses`, `Certificates`, `Store`, `Prints`, or `Canteen`.
* **Status Badges:** Color-coded status flags (`PENDING`, `APPROVED`, `REJECTED`, `PRINTING`, `READY`, `SERVED`).
* **Timestamp & Detail Cards:** Clear breakdown of reason, items, costs, and dates.

#### How to Use:
1. Tap **Track Requests** on the Dashboard.
2. Tap any filter chip at the top to isolate specific categories.
3. Tap on any transaction card to view full details or receipts.

---

### 4.11 Screen 11: Academic Timetable & Attendance System

> 📸 **SCREENSHOT PLACEHOLDER #11: Timetable & Attendance System**
> ```
> +-------------------------------------------------------+
> |   Time Table & Attendance                             |
> |   [ Mon ]  [ Tue ]  [ Wed ]  [ Thu ]  [ Fri ]  [ Sat ]|
> |                                                       |
> |   Overall Attendance Gauge:  88.0%  (Target: >75%)    |
> |   [██████████████████████████████░░░░]                |
> |                                                       |
> |   Today's Schedule (Monday):                          |
> |   +-------------------------------------------------+ |
> |   | 09:00 - 10:00 AM | CS301 | Data Structures      | |
> |   | Room 204 • Prof. V. Krishna        [Completed]  | |
> |   +-------------------------------------------------+ |
> |   | 10:00 - 11:00 AM | CS302 | AI & Machine Learning| |
> |   | Room 302 • Dr. Arshia Khan         [Ongoing]    | |
> |   +-------------------------------------------------+ |
> |   | 12:15 - 01:15 PM | Cafeteria       [Lunch Break]| |
> |   +-------------------------------------------------+ |
> |                                                       |
> |   Classroom Notices:                                  |
> |   📢 AI Lab Relocation Notice - Dr. Arshia Khan       |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/11_timetable_screen.png`*  
> *Caption: Academic timetable screen showing day selector, overall attendance meter, period schedules, and classroom notice board.*

#### Purpose:
Provides students with their interactive daily schedule, subject-by-subject attendance percentages, faculty presence records, and immediate classroom notices.

#### Visual Elements & Buttons:
* **Day Selector:** Monday through Saturday tabs.
* **Overall Attendance Gauge:** Live progress bar displaying total attendance percentage compared against the mandatory 75% college requirement.
* **Period Schedule Cards:** Displays time slot, subject code, subject title, room number, faculty name, and status (`Completed`, `Ongoing`, `Upcoming`, or `Break`).
* **Subject Attendance Breakdown:** Shows attended versus total conducted classes for each subject.
* **Faculty Attendance Register:** Displays faculty presence (`Present Today`, `On Duty`, `On Leave`).
* **Classroom Notice Board:** Urgent schedule relocations and announcements posted by faculty and HOD.
* **Role-Based Edit Capabilities:** HODs, faculty advisors, and designated student **Class Representatives (CR)** or **Ladies Representatives (LR)** have special privileges to update timetable notices.

#### How to Use:
1. Tap on the day of the week to inspect scheduled periods.
2. Review your subject attendance percentage to ensure you stay above 75%.
3. Check the **Classroom Notices** section at the bottom for room changes or lab announcements.

---

### 4.12 Screen 12: Training & Placement Cell (TPC) Hub

> 📸 **SCREENSHOT PLACEHOLDER #12: Training & Placement Cell (TPC) Hub**
> ```
> +-------------------------------------------------------+
> |   Training & Placement Cell (TPC)                     |
> |   Dynamic recruitment channels & faculty directory    |
> | ----------------------------------------------------- |
> |   [ Staff & Depts ] [ Job Drives ] [ Applications ]   |
> |                                                       |
> |   Active Campus Recruitment Drives:                   |
> |   +-------------------------------------------------+ |
> |   | Tata Consultancy Services (TCS)                 | |
> |   | Role: System Engineer                           | |
> |   | Package: 7.0 LPA • Min CGPA: 7.0                | |
> |   | Deadline: Next Friday                           | |
> |   | [ View Criteria ]                [ Apply Now ]  | |
> |   +-------------------------------------------------+ |
> |   | Amazon AWS                                      | |
> |   | Role: Cloud Support Associate                   | |
> |   | Package: 14.5 LPA • Min CGPA: 8.0               | |
> |   | [ View Criteria ]                [ Apply Now ]  | |
> |   +-------------------------------------------------+ |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/12_tpc_screen.png`*  
> *Caption: Training & Placement Cell hub showing active recruitment drives, CTC packages, criteria, and staff directory.*

#### Purpose:
The central placement hub for engineering students to explore campus recruitment drives, verify CGPA eligibility, and track interview rounds.

#### Visual Elements & Buttons:
* **Staff & Departments Tab:** Complete faculty and coordinator directory for all engineering branches.
* **Job Drives Tab:** Browse hiring companies, job titles, CTC packages (LPA), eligibility criteria, and application deadlines.
* **Apply Button:** Checks the student's recorded CGPA against the company's minimum cutoff and submits their resume.
* **Applications Tab:** Tracks hiring rounds:
  `Applied` ➔ `Shortlisted` ➔ `Technical Interview` ➔ `HR Interview` ➔ `Offered`.
* **Profile / Metrics Tab:** Displays student CGPA records, resume links, and institutional placement statistics.

#### How to Use:
1. Tap the **Job Drives** tab.
2. Read the job description and package details.
3. Tap **Apply Now**. The app checks your eligibility and registers your profile with the placement officer.

---

### 4.13 Screen 13: Staff, Faculty & HOD Approval Console

> 📸 **SCREENSHOT PLACEHOLDER #13: Staff & HOD Approval Console**
> ```
> +-------------------------------------------------------+
> |   Staff Console (Head of Department)                  |
> |   [ Allocations ] [ Outpasses ] [ Certs ] [ History ] |
> |                                                       |
> |   PENDING OUTPASS APPROVALS (Level 2):                |
> |   +-------------------------------------------------+ |
> |   | Alex Rivera (23CS101) - Computer Science        | |
> |   | Reason: Family emergency / Weekend visit        | |
> |   | Time: Today, 04:00 PM (Return: 08:30 PM)        | |
> |   | Advisor Endorsement: APPROVED BY DR. FINCH      | |
> |   |                                                 | |
> |   |       [ ❌ Reject ]         [ ✅ Approve ]       | |
> |   +-------------------------------------------------+ |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/13_staff_console_screen.png`*  
> *Caption: HOD approval console displaying pending student outpass requests with advisor endorsement verification and action buttons.*

#### Purpose:
The specialized dashboard for Class Advisors, Mentors, and HODs to review, endorse, or reject student requests.

#### Visual Elements & Buttons:
* **Pending Outpasses Queue:** Displays student name, roll number, department, departure time, and reason.
* **Advisor Endorsement Check:** Shows whether the student's Class Advisor has already cleared the request.
* **Approve Button (✅):** Advances the request to the next stage or generates the student's gate QR code.
* **Reject Button (❌):** Opens an explanation dialog to enter mandatory remarks explaining why the request was denied.
* **Certificate Endorsement Tab:** Allows HODs and Principals to authorize formal transcripts and certificates.

#### How to Use:
1. Log in with an Advisor (`advisor`) or HOD (`hod`) account.
2. Tap **Student Requests** or **Staff Console**.
3. Review pending requests. Tap **Approve** to authorize the student's campus exit.

---

### 4.14 Screen 14: Gate Security QR Verification Scanner

> 📸 **SCREENSHOT PLACEHOLDER #14: Gate Security QR Verification Scanner**
> ```
> +-------------------------------------------------------+
> |   Gate Security Console                               |
> |   [ Outpasses (Queue) ]   [ Camera QR Scanner ]       |
> |                                                       |
> |   +-------------------------------------------------+ |
> |   | [ LIVE CAMERA VIEWFINDER / QR SCANNER TARGET ]  | |
> |   | Align student outpass QR code inside frame      | |
> |   +-------------------------------------------------+ |
> |                                                       |
> |   Manual Token Verification:                          |
> |   Enter Outpass Code: [ OP-9042           ] [ Verify ]|
> |                                                       |
> |   LAST VERIFIED STUDENT:                              |
> |   ✅ Alex Rivera (23CS101)                            |
> |   Status: CLEARED FOR EXIT • Time: 04:02 PM           |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/14_gate_security_screen.png`*  
> *Caption: Gate security verification screen showing camera QR scanner viewfinder, manual token fallback, and verified student card.*

#### Purpose:
Used by security personnel stationed at the college entry/exit gates to authenticate students leaving the campus.

#### Visual Elements & Buttons:
* **Camera Scanner Viewfinder:** Built-in scanner to read encrypted QR codes directly from student phone screens.
* **Manual Code Input Field:** Fallback option to type the 6-character outpass ticket number if a student's phone screen is cracked or low on battery.
* **Verification Result Card:** Instantly shows student photo, roll number, parent contact number, departure reason, and approval signatures.
* **Mark Exit / Entry Completed Button:** Logs the exact exit time and closes the outpass ticket in the database.

#### How to Use:
1. Log in with the Security account (`security@necsecurity.in`).
2. Point the device camera at the student's approved **Outpass QR Code**.
3. Verify the student's details on the screen and tap **Authorize Gate Exit**.

---

### 4.15 Screen 15: Canteen Manager Orders & Token Redemption

> 📸 **SCREENSHOT PLACEHOLDER #15: Canteen Staff Hub & Order Queue**
> ```
> +-------------------------------------------------------+
> |   Canteen Staff Hub                     [Analytics]   |
> |   Live Order Queue & Token Redemption Desk            |
> | ----------------------------------------------------- |
> |   Redeem Food Token:                                  |
> |   Enter Token Code: [ TKN-8421            ] [ Redeem ]|
> |                                                       |
> |   ACTIVE INCOMING ORDERS (Queue: 3):                  |
> |   +-------------------------------------------------+ |
> |   | Token: #TKN-8421 | Student: Alex Rivera         | |
> |   | Items: 1x Veg Meals (Full), 1x Lime Soda        | |
> |   | Total: ₹100.00 | Status: BOOKED                 | |
> |   |                                                 | |
> |   |            [ Mark as Served / Delivered ]       | |
> |   +-------------------------------------------------+ |
> |                                                       |
> |   Today's Sales Revenue: ₹4,850.00 • Tokens Served: 64|
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/15_canteen_staff_hub.png`*  
> *Caption: Canteen staff console showing incoming food preparation queue, token redemption field, and daily sales totals.*

#### Purpose:
Used by the college cafeteria staff to view incoming student orders, prepare meals before students arrive, and redeem food collection tokens.

#### Visual Elements & Buttons:
* **Token Redemption Field:** Type or scan the student's meal token to pull up their ordered items.
* **Incoming Orders Queue:** Live list of pre-booked orders arranged chronologically.
* **Itemized Receipt Breakdown:** Shows exact quantities (e.g. `2x Idli`, `1x Coffee`).
* **Mark as Served Button:** Marks the order completed and invalidates the token to prevent duplicate pickups.
* **Daily Sales Metric Bar:** Tallies total earnings in Rupees (₹) and count of meals served today.

#### How to Use:
1. Log in with the Canteen account (`canteen@neccanteen.com`).
2. View the incoming order queue and prepare meals in advance.
3. When the student presents their token, tap **Mark as Served**.

---

### 4.16 Screen 16: Stationery & Printing Staff Hub

> 📸 **SCREENSHOT PLACEHOLDER #16: Stationery & Printing Staff Hub**
> ```
> +-------------------------------------------------------+
> |   Stationery & Printing Operations Hub                |
> |   [ Print Queue (2) ]     [ Stationery Orders (1) ]   |
> |                                                       |
> |   INCOMING PRINT JOBS:                                |
> |   +-------------------------------------------------+ |
> |   | File: AI_Project_Report.pdf                     | |
> |   | Student: Emily Watson (23EC204)                 | |
> |   | Specs: 24 pgs • Color • Spiral Binding          | |
> |   | Total Cost: ₹280.00 • Status: QUEUED            | |
> |   |                                                 | |
> |   |  [ Start Printing ]    [ Mark Ready for Pickup ]| |
> |   +-------------------------------------------------+ |
> |                                                       |
> |   Inventory Quick Add:                                |
> |   Item: [ Standard Gel Pens ]  Add Qty: [ 50 ] [Add]  |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/16_printing_staff_hub.png`*  
> *Caption: Printing and stationery staff hub showing pending print jobs, spiral binding specifications, and inventory restock tools.*

#### Purpose:
Used by store employees to download submitted PDF documents, execute print jobs, and manage stationery inventory stock.

#### Visual Elements & Buttons:
* **Print Queue Tab:** Displays files waiting to be printed, page counts, color choices, and binding requirements.
* **Start Printing Button:** Changes status to `PRINTING` and sends an alert to the student's phone.
* **Mark Ready for Pickup Button:** Updates status to `READY` when copies are bound and ready at the counter.
* **Stationery Orders Tab:** View pre-ordered textbooks and records waiting to be packed.
* **Inventory Quick Add Form:** Select any stationery product and increment stock numbers instantly.

#### How to Use:
1. Log in with the Store account (`store@necstationary.in`).
2. Review incoming print jobs. Check if spiral binding is required.
3. Print the document and click **Mark Ready for Pickup**.

---

### 4.17 Screen 17: Super Administrator Master Portal

> 📸 **SCREENSHOT PLACEHOLDER #17: Super Administrator Portal**
> ```
> +-------------------------------------------------------+
> |   Master Administrator Console                        |
> |   [ Allocations ] [ Manage Employees ] [ Analytics ]  |
> |                                                       |
> |   BATCH STUDENT CSV IMPORT:                           |
> |   [ 📁 Upload Student Records CSV File ]              |
> |   Auto-creates roll numbers, emails, and passwords    |
> |                                                       |
> |   MENTOR & ADVISOR MAPPING:                           |
> |   Student: [ Alex Rivera (23CS101)                  ] |
> |   Assign Class Advisor: [ Dr. Robert Finch          ] |
> |   Assign Academic Mentor: [ Prof. Sarah Miller      ] |
> |   [ Save Allocations ]                                |
> |                                                       |
> |   Campus Overview: 1,420 Students • 84 Faculty        |
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/17_admin_portal.png`*  
> *Caption: Master administrator console showing CSV batch import, mentor-student mapping, and system telemetry.*

#### Purpose:
The complete administrative nerve center for college IT administrators to configure academic sessions, import student rosters, and assign mentors.

#### Visual Elements & Buttons:
* **CSV Batch Student Import Tool:** Lets administrators upload a `.csv` spreadsheet containing hundreds of student records to create accounts automatically in bulk.
* **Mentor & Class Advisor Allocation Matrix:** Dropdown menus to map students to specific faculty advisors.
* **Manage Employees Tab:** Add, edit, or de-activate faculty and staff accounts.
* **Full Audit Log & System Analytics:** Comprehensive charts illustrating outpass traffic, certificate delivery times, and canteen revenue.

#### How to Use:
1. Log in with the Admin account (`admin@ranbidge.com`).
2. Tap **Allocations** to assign newly admitted students to their Class Advisor.
3. Tap **Upload CSV** to populate entire classroom rosters in seconds.

---

### 4.18 Screen 18: Firebase Cloud Sync & Database Telemetry

> 📸 **SCREENSHOT PLACEHOLDER #18: Firebase Cloud Sync Hub**
> ```
> +-------------------------------------------------------+
> |   Firebase Cloud Sync Hub                             |
> |   Real-time telemetry & database synchronization      |
> | ----------------------------------------------------- |
> |   +-------------------------------------------------+ |
> |   | ☁️ Firebase Database Link: CONNECTED            | |
> |   | Cloud Firestore Status: Synchronized & Live     | |
> |   +-------------------------------------------------+ |
> |                                                       |
> |   SYNCHRONIZATION TELEMETRY:                          |
> |   • Users Synchronized:        12 Accounts            |
> |   • Outpass Records:           24 Transactions        |
> |   • Certificates:              18 Applications        |
> |   • Canteen Bookings:          45 Tokens              |
> |   • Stationery Orders:         32 Requests            |
> |                                                       |
> |   [ Test Live Connection ]    [ Force Full Cloud Sync]|
> +-------------------------------------------------------+
> ```
> *Insert screenshot here: `screenshots/18_firebase_hub_screen.png`*  
> *Caption: Firebase cloud sync screen displaying bidirectional sync telemetry between local Room DB and Cloud Firestore.*

#### Purpose:
Provides live diagnostics and cloud synchronization metrics between the local SQLite database on the phone and Google Cloud Firestore.

#### Visual Elements & Buttons:
* **Connection Status Card:** Displays green (`Connected`) or red (`Offline`) with connection health indicators.
* **Telemetry Counters:** Shows exact count of users, outpasses, certificates, print jobs, and food tokens synced with the cloud.
* **Test Live Connection Button:** Pings Cloud Firestore to verify latency and security rule permissions.
* **Force Full Cloud Sync Button:** Manually triggers a bidirectional sync to ensure offline records are uploaded immediately.

#### How to Use:
1. Tap the cloud status badge on the Print or Dashboard screen, or navigate to **Firebase Hub**.
2. Tap **Test Live Connection** to verify database synchronization.

---

## 5. END-TO-END WORKFLOW EXAMPLES

To understand how the app works in real college scenarios, here are three complete walkthroughs:

### Scenario A: A Student Leaves Campus for a Medical Emergency
```
[ Step 1: Student ]
Alex opens Campus Connect -> Taps 'Outpass Clearance' -> Selects departure 'Today 04:00 PM' -> 
Enters Reason: 'Doctor Appointment' -> Submits request.
       │
       ▼
[ Step 2: Class Advisor ]
Dr. Finch receives a push notification -> Opens 'Staff Console' -> Reviews Alex's reason -> 
Taps 'Approve'. Request moves to Level 2.
       │
       ▼
[ Step 3: HOD ]
Prof. Vance opens 'Staff Console' -> Sees advisor endorsement -> Taps 'Approve'. 
An encrypted gate QR pass is generated instantly on Alex's phone.
       │
       ▼
[ Step 4: Gate Security ]
Alex reaches the college gate -> Shows the QR code on his phone -> Security Officer Bahadur 
scans the code with the app camera -> Details verify -> Security taps 'Authorize Exit'.
Ticket is marked COMPLETED.
```

---

### Scenario B: Pre-Ordering Lunch at the Canteen
```
[ Step 1: Browse ]
At 12:00 PM, Emily opens 'Canteen Token' -> Selects 'Lunch' -> Adds 1x Veg Meals (₹80) to cart.
       │
       ▼
[ Step 2: Checkout ]
Emily taps 'Checkout' -> Confirms order -> The app generates unique QR Token #TKN-8421.
       │
       ▼
[ Step 3: Kitchen Prepares ]
Canteen Chef Ram sees #TKN-8421 in the incoming live queue -> Packages the hot meal.
       │
       ▼
[ Step 4: Quick Pickup ]
At 12:30 PM, Emily walks to the pickup window -> Shows QR Token #TKN-8421 -> Canteen staff 
scans/taps 'Mark as Served' -> Emily receives her lunch in under 30 seconds!
```

---

### Scenario C: Printing a 20-Page Project Report with Spiral Binding
```
[ Step 1: Upload ]
Student uploads 'Compiler_Design_Report.pdf' -> Enters 20 pages -> Selects 'Color' and 'Spiral Binding'.
       │
       ▼
[ Step 2: Instant Cost Calculation ]
The app computes: (20 pages × ₹10.00) + ₹40.00 binding = ₹240.00 total.
       │
       ▼
[ Step 3: Print Desk Fulfillment ]
Store clerk Mr. Rajan sees the job in his queue -> Downloads and prints the 20 color pages -> 
Binds them with spiral cover -> Clicks 'Mark Ready for Pickup'.
       │
       ▼
[ Step 4: Notification & Collection ]
Student receives a notification: 'Your report is ready!' -> Collects the bound report at the counter.
```

---

## 6. TROUBLESHOOTING & FREQUENTLY ASKED QUESTIONS (FAQ)

### Q1: The app does not compile in Android Studio. What should I check?
* **Answer:** Make sure you are using **JDK 17** or **JDK 21**. In Android Studio, go to `Settings > Build, Execution, Deployment > Build Tools > Gradle` and ensure the **Gradle JDK** is set to version 17 or 21. Then click `File > Sync Project with Gradle Files`.

### Q2: How do I test the Gate Security scanner without a physical camera on the emulator?
* **Answer:** The Gate Security screen provides a **Manual Code Input Field**. You can simply type the outpass code (e.g. `OP-9042`) and click **Verify** to simulate scanning!

### Q3: Can I run this app without an internet connection?
* **Answer:** Yes! The application is built with an offline-first architecture using **Room Database (SQLite)**. All user profiles, outpass tickets, timetables, and orders are stored locally on the phone. When internet is restored, it automatically synchronizes with Google Cloud Firestore.

### Q4: I forgot the password for a demo account. How do I log in?
* **Answer:** For all demo accounts (`student`, `advisor`, `hod`, `principal`, `security`, `canteen`, `store`, `admin`), the password is `password`. You can also enter the user's ID as the password.

### Q5: How do I take screenshots to paste into this manual?
* **Answer:** While running the app in Android Studio (on an emulator or connected phone), look at the top-left of the **Logcat** or **Running Devices** window and click the small **Camera Icon (Screen Capture)**. Save the images into a `screenshots/` folder and paste them under each placeholder in this manual!

---

## 7. CONCLUSION & PROJECT SUMMARY

**Campus Connect** successfully transforms traditional college administration into a modern, transparent, and completely digital ecosystem. By uniting 8 institutional roles into a single intuitive app, it guarantees:
1. **Zero Paper Waste:** Outpasses, letters, and orders are 100% digital.
2. **Speed & Efficiency:** Approvals happen in seconds without walking across buildings.
3. **Ironclad Gate Security:** Encrypted QR passes prevent unauthorized student departures.
4. **Delightful Student Experience:** Smooth canteen booking, print queues, and live attendance tracking.

---

<p align="center">
  <b>Prepared by RANBIDGE Solutions Private Limited</b><br>
  <i>Lead Developers: R GOPINATH REDDY & V KONDA REDDY</i><br>
  <b>For Narasaraopeta Engineering College (NEC)</b>
</p>
