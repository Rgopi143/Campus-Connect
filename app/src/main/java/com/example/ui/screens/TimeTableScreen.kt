package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.viewmodel.PortalViewModel
import com.example.data.model.User

data class TimetablePeriod(
    val id: Int,
    val timeSlot: String,
    val subjectName: String,
    val subjectCode: String,
    val roomNo: String,
    val facultyName: String,
    val status: String // Completed, Ongoing, Upcoming, Break
)

data class SubjectAttendance(
    val subjectName: String,
    val subjectCode: String,
    val attended: Int,
    val total: Int
) {
    val percentage: Float get() = if (total > 0) (attended.toFloat() / total * 100) else 0f
}

data class FacultyAttendanceRecord(
    val name: String,
    val designation: String,
    val department: String,
    val scheduledClasses: Int,
    val conductedClasses: Int,
    val status: String // Present Today, On Duty, On Leave
) {
    val percentage: Float get() = if (scheduledClasses > 0) (conductedClasses.toFloat() / scheduledClasses * 100) else 0f
}

data class ClassroomNoticeMessage(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val role: String,
    val timeAgo: String,
    val tag: String // Schedule Change, Urgent, General
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableScreen(
    viewModel: PortalViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentUser by viewModel.currentUser.collectAsState()
    val allUsers by viewModel.allUsers.collectAsState()

    // --- ACCESS CONTROL & PERMISSION CALCULATIONS ---
    val isHod = currentUser?.role == "HOD"
    val isFacultyOrAdmin = currentUser?.role in listOf("HOD", "CLASS_ADVISOR", "MENTOR", "ADMIN", "PRINCIPAL")
    
    val desig = currentUser?.studentDesignation
    val isCR = desig == "CR" || desig == "Class Representative"
    val isLR = desig == "LR" || desig == "Ladies Representative"
    val isDesignatedRepresentative = currentUser?.role == "STUDENT" && (isCR || isLR)

    // Regular students have READ-ONLY access. Edit permissions are granted to HOD/Faculty and assigned CR / LR students.
    val canEdit = isFacultyOrAdmin || isDesignatedRepresentative

    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    var selectedDay by remember { mutableStateOf("Monday") }

    // State for timetable schedule items
    var timetableSchedule by remember {
        mutableStateOf(
            listOf(
                TimetablePeriod(1, "09:00 - 10:00 AM", "Data Structures & Algorithms", "CS301", "Room 204", "Prof. V. Krishna", "Completed"),
                TimetablePeriod(2, "10:00 - 11:00 AM", "Artificial Intelligence & ML", "CS302", "Room 302", "Dr. Arshia Khan", "Ongoing"),
                TimetablePeriod(3, "11:15 - 12:15 PM", "Database Management Systems", "CS303", "Room 204", "Dr. Priya Nair", "Upcoming"),
                TimetablePeriod(4, "12:15 - 01:15 PM", "Lunch & Refreshments Break", "LUNCH", "Cafeteria", "Self", "Break"),
                TimetablePeriod(5, "01:15 - 02:15 PM", "Web Dev & DevOps Lab", "CS304L", "Computer Lab 2", "Mr. John Wesley", "Upcoming"),
                TimetablePeriod(6, "02:15 - 03:15 PM", "Operating Systems & Kernels", "CS305", "Room 204", "Mr. Rajesh Kurup", "Upcoming")
            )
        )
    }

    // Student Attendance data
    val studentAttendanceList = remember {
        listOf(
            SubjectAttendance("Data Structures & Algorithms", "CS301", 23, 25),
            SubjectAttendance("Artificial Intelligence & ML", "CS302", 22, 25),
            SubjectAttendance("Database Management Systems", "CS303", 21, 25),
            SubjectAttendance("Web Dev & DevOps Lab", "CS304L", 24, 25),
            SubjectAttendance("Operating Systems & Kernels", "CS305", 20, 25)
        )
    }

    val totalAttended = studentAttendanceList.sumOf { it.attended }
    val totalConducted = studentAttendanceList.sumOf { it.total }
    val overallPercentage = if (totalConducted > 0) (totalAttended.toFloat() / totalConducted * 100) else 0f

    // Faculty Attendance data
    val facultyAttendanceList = remember {
        listOf(
            FacultyAttendanceRecord("Prof. V. Krishna", "Professor & HOD", "Computer Science", 45, 43, "Present Today"),
            FacultyAttendanceRecord("Dr. Arshia Khan", "Professor & Head", "CSE (Emerging Tech)", 40, 39, "Present Today"),
            FacultyAttendanceRecord("Dr. Priya Nair", "Associate Professor", "Computer Science", 38, 36, "Present Today"),
            FacultyAttendanceRecord("Mr. John Wesley", "Assistant Professor", "Computer Science", 42, 41, "Present Today"),
            FacultyAttendanceRecord("Mr. Rajesh Kurup", "Assistant Professor", "CSE (Emerging Tech)", 35, 34, "On Duty")
        )
    }

    // Classroom Notices & Messages list
    var noticeMessages by remember {
        mutableStateOf(
            listOf(
                ClassroomNoticeMessage(
                    id = 1,
                    title = "AI & ML Lab Relocation Notice",
                    content = "The Tuesday AI & ML Lab session has been relocated to Computer Lab 302 due to hardware maintenance in Lab 1.",
                    author = "Dr. Arshia Khan",
                    role = "Head of Department",
                    timeAgo = "10 mins ago",
                    tag = "Schedule Change"
                ),
                ClassroomNoticeMessage(
                    id = 2,
                    title = "Mandatory 75% Attendance Requirement",
                    content = "Students are advised to maintain at least 75% attendance across all core subjects to receive Mid-1 Examination hall tickets.",
                    author = "Prof. V. Krishna",
                    role = "Class Advisor",
                    timeAgo = "Yesterday, 04:30 PM",
                    tag = "Urgent"
                ),
                ClassroomNoticeMessage(
                    id = 3,
                    title = "DBMS Remedial Class Announcement",
                    content = "An optional extra problem-solving session for DBMS SQL queries is scheduled for Friday at 03:30 PM in Seminar Hall B.",
                    author = "Dr. Priya Nair",
                    role = "Faculty Coordinator",
                    timeAgo = "2 days ago",
                    tag = "General"
                )
            )
        )
    }

    var showPostMessageDialog by remember { mutableStateOf(false) }
    var newMsgTitle by remember { mutableStateOf("") }
    var newMsgContent by remember { mutableStateOf("") }
    var newMsgTag by remember { mutableStateOf("Schedule Change") }

    var showHodAssignDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Banner & Access Permissions Summary
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.85f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("timetable_header_card")
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Schedule,
                                        contentDescription = "Time Table Icon",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Time Table & Attendance",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "Class schedules, student/faculty attendance logs & classroom notices.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                )
                            }
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f))

                        // Access Permission Status Indicator Banner
                        if (isHod) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AdminPanelSettings,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Text(
                                            text = "👑 HOD Console: Full Control & Representative Assignment Access",
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    TextButton(
                                        onClick = { showHodAssignDialog = true },
                                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Yellow)
                                    ) {
                                        Text("Assign CR / LR", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        } else if (isDesignatedRepresentative) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFF2E7D32),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VerifiedUser,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "🏆 Assigned Representative: ${if (isCR) "Class Representative (CR)" else "Ladies Representative (LR)"} • Edit Access Granted",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "ℹ️ Read-Only View: All students have read access. Edit rights are assigned by HOD to Class Representatives (CR / LR).",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // --- HOD ASSIGNMENT PANEL (FOR HOD USER ONLY) ---
            if (isHod) {
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25f)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("hod_representative_panel")
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SupervisorAccount,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                    Text(
                                        text = "Student Representative Assignment (HOD)",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Button(
                                    onClick = { showHodAssignDialog = true },
                                    modifier = Modifier.height(34.dp)
                                ) {
                                    Text("Manage CR / LR", fontSize = 11.sp)
                                }
                            }

                            Text(
                                text = "Assign Class Representative (CR) and Ladies Representative (LR) students to delegate timetable & attendance edit permissions.",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            // List active assigned CR/LR students
                            val assignedReps = allUsers.filter { it.role == "STUDENT" && !it.studentDesignation.isNull_or_empty() }
                            if (assignedReps.isEmpty()) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "No Class Representatives (CR / LR) assigned yet. Click 'Manage CR / LR' above to allocate.",
                                        fontSize = 11.sp,
                                        color = Color.Gray,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    assignedReps.forEach { student ->
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = MaterialTheme.colorScheme.surface,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(10.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Column {
                                                    Text(
                                                        text = student.name,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 12.sp
                                                    )
                                                    Text(
                                                        text = "${student.rollNumber} • ${student.department}",
                                                        fontSize = 10.sp,
                                                        color = Color.Gray
                                                    )
                                                }
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                                ) {
                                                    Surface(
                                                        shape = RoundedCornerShape(6.dp),
                                                        color = if (student.studentDesignation == "CR") MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer
                                                    ) {
                                                        Text(
                                                            text = if (student.studentDesignation == "CR") "Class Rep (CR)" else "Ladies Rep (LR)",
                                                            fontSize = 10.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                        )
                                                    }
                                                    IconButton(
                                                        onClick = {
                                                            viewModel.assignStudentDesignation(student.userId, null)
                                                            Toast.makeText(context, "Revoked representative role for ${student.name}", Toast.LENGTH_SHORT).show()
                                                        },
                                                        modifier = Modifier.size(24.dp)
                                                    ) {
                                                        Icon(Icons.Default.Close, contentDescription = "Revoke", tint = Color.Red, modifier = Modifier.size(16.dp))
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // --- BLOCK 1: TIME TABLE SCHEDULE BLOCK ---
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("timetable_schedule_block")
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CalendarMonth,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Class Schedule Timetable",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            AssistChip(
                                onClick = { },
                                label = { Text("Semester III", fontSize = 10.sp) },
                                leadingIcon = {
                                    Icon(Icons.Default.School, contentDescription = null, modifier = Modifier.size(12.dp))
                                }
                            )
                        }

                        // Day selector chips
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(daysOfWeek) { day ->
                                FilterChip(
                                    selected = selectedDay == day,
                                    onClick = { selectedDay = day },
                                    label = { Text(day, fontWeight = FontWeight.SemiBold) },
                                    leadingIcon = if (selectedDay == day) {
                                        { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(14.dp)) }
                                    } else null
                                )
                            }
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                        // Period list
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            timetableSchedule.forEach { period ->
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = when (period.status) {
                                        "Ongoing" -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                        "Completed" -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        "Break" -> MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
                                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.width(90.dp)
                                        ) {
                                            Text(
                                                text = period.timeSlot,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 10.sp,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = when (period.status) {
                                                    "Ongoing" -> MaterialTheme.colorScheme.primary
                                                    "Completed" -> Color(0xFF4CAF50)
                                                    "Break" -> MaterialTheme.colorScheme.tertiary
                                                    else -> MaterialTheme.colorScheme.secondary
                                                }
                                            ) {
                                                Text(
                                                    text = period.status,
                                                    color = Color.White,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }

                                        HorizontalDivider(
                                            modifier = Modifier
                                                .height(40.dp)
                                                .width(1.dp),
                                            color = MaterialTheme.colorScheme.outlineVariant
                                        )

                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = period.subjectName,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 13.sp,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "${period.subjectCode} • ${period.facultyName}",
                                                fontSize = 11.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Text(
                                                text = "Location: ${period.roomNo}",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // --- BLOCK 2: ATTENDANCE CONTAINER BLOCK ---
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("attendance_main_block")
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.FactCheck,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Attendance Monitor & Analytics",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (overallPercentage >= 75f) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                            ) {
                                Text(
                                    text = if (overallPercentage >= 75f) "Eligible" else "Low Attendance",
                                    color = if (overallPercentage >= 75f) Color(0xFF2E7D32) else Color(0xFFC62828),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        // --- SUB-BLOCK 2A: STUDENT ATTENDANCE BLOCK ---
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.12f)
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("student_attendance_block")
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Text(
                                            text = "Student Attendance Summary",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    Text(
                                        text = "${overallPercentage.toInt()}% Overall",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (overallPercentage >= 75f) Color(0xFF2E7D32) else Color(0xFFC62828)
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Total Attended: $totalAttended / $totalConducted sessions",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "Required: Min 75%",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Gray
                                    )
                                }

                                LinearProgressIndicator(
                                    progress = { (overallPercentage / 100f).coerceIn(0f, 1f) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .clip(CircleShape),
                                    color = if (overallPercentage >= 75f) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error,
                                    trackColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                )

                                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

                                Text(
                                    text = "Subject-Wise Breakdown:",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                studentAttendanceList.forEach { item ->
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "${item.subjectCode}: ${item.subjectName}",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Text(
                                                text = "${item.attended}/${item.total} (${item.percentage.toInt()}%)",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (item.percentage >= 75f) Color(0xFF2E7D32) else Color(0xFFC62828)
                                            )
                                        }
                                        LinearProgressIndicator(
                                            progress = { (item.percentage / 100f).coerceIn(0f, 1f) },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(6.dp)
                                                .clip(CircleShape),
                                            color = if (item.percentage >= 75f) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                                        )
                                    }
                                }
                            }
                        }

                        // --- SUB-BLOCK 2B: FACULTY ATTENDANCE BLOCK ---
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.15f)
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("faculty_attendance_block")
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Face,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.secondary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Text(
                                            text = "Faculty Attendance & Class Logs",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                    }
                                    Text(
                                        text = "96.4% Compliance",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }

                                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

                                facultyAttendanceList.forEach { fac ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = fac.name,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "${fac.designation} • ${fac.department}",
                                                fontSize = 10.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Text(
                                                text = "Classes Conducted: ${fac.conductedClasses}/${fac.scheduledClasses} (${fac.percentage.toInt()}%)",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = when (fac.status) {
                                                "Present Today" -> Color(0xFFE8F5E9)
                                                "On Duty" -> Color(0xFFE3F2FD)
                                                else -> Color(0xFFFFEBEE)
                                            }
                                        ) {
                                            Text(
                                                text = fac.status,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = when (fac.status) {
                                                    "Present Today" -> Color(0xFF2E7D32)
                                                    "On Duty" -> Color(0xFF1565C0)
                                                    else -> Color(0xFFC62828)
                                                },
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // --- BLOCK 3: MESSAGE BLOCK ---
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("classroom_message_block")
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Campaign,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Classroom Notices & Messages",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            if (canEdit) {
                                IconButton(
                                    onClick = { showPostMessageDialog = true },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AddComment,
                                        contentDescription = "Post Message",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                        noticeMessages.forEach { msg ->
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = msg.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = when (msg.tag) {
                                                "Urgent" -> MaterialTheme.colorScheme.errorContainer
                                                "Schedule Change" -> MaterialTheme.colorScheme.primaryContainer
                                                else -> MaterialTheme.colorScheme.tertiaryContainer
                                            }
                                        ) {
                                            Text(
                                                text = msg.tag,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }

                                    Text(
                                        text = msg.content,
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "By ${msg.author} (${msg.role})",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                        Text(
                                            text = msg.timeAgo,
                                            fontSize = 10.sp,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // --- HOD MANAGE REPRESENTATIVES DIALOG ---
    if (showHodAssignDialog) {
        val studentList = allUsers.filter { it.role == "STUDENT" }
        AlertDialog(
            onDismissRequest = { showHodAssignDialog = false },
            title = {
                Text(text = "Assign Class / Ladies Representatives (CR / LR)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            },
            text = {
                Column(
                    modifier = Modifier.heightIn(max = 350.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Select a student to grant Class Representative (CR) or Ladies Representative (LR) edit access for timetable & attendance.",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(studentList) { student ->
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = student.name,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp
                                            )
                                            Text(
                                                text = "${student.rollNumber} • ${student.department}",
                                                fontSize = 10.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        student.studentDesignation?.let { curDesig ->
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = MaterialTheme.colorScheme.primary
                                            ) {
                                                Text(
                                                    text = curDesig,
                                                    fontSize = 10.sp,
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        OutlinedButton(
                                            onClick = {
                                                viewModel.assignStudentDesignation(student.userId, "CR")
                                                Toast.makeText(context, "Assigned ${student.name} as Class Representative (CR)", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier.weight(1f).height(32.dp),
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text("Assign CR", fontSize = 10.sp)
                                        }

                                        OutlinedButton(
                                            onClick = {
                                                viewModel.assignStudentDesignation(student.userId, "LR")
                                                Toast.makeText(context, "Assigned ${student.name} as Ladies Representative (LR)", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier.weight(1f).height(32.dp),
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text("Assign LR", fontSize = 10.sp)
                                        }

                                        if (student.studentDesignation != null) {
                                            TextButton(
                                                onClick = {
                                                    viewModel.assignStudentDesignation(student.userId, null)
                                                    Toast.makeText(context, "Removed designation for ${student.name}", Toast.LENGTH_SHORT).show()
                                                },
                                                modifier = Modifier.height(32.dp),
                                                contentPadding = PaddingValues(0.dp)
                                            ) {
                                                Text("Revoke", fontSize = 10.sp, color = Color.Red)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showHodAssignDialog = false }) {
                    Text("Done")
                }
            }
        )
    }

    // Post Message Dialog
    if (showPostMessageDialog && canEdit) {
        AlertDialog(
            onDismissRequest = { showPostMessageDialog = false },
            title = {
                Text(text = "Post Classroom Message", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = newMsgTitle,
                        onValueChange = { newMsgTitle = it },
                        label = { Text("Title / Topic") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newMsgContent,
                        onValueChange = { newMsgContent = it },
                        label = { Text("Message Content") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Category:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        listOf("Schedule Change", "Urgent", "General").forEach { tag ->
                            FilterChip(
                                selected = newMsgTag == tag,
                                onClick = { newMsgTag = tag },
                                label = { Text(tag, fontSize = 10.sp) }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newMsgTitle.isBlank() || newMsgContent.isBlank()) {
                            Toast.makeText(context, "Title and Content cannot be empty", Toast.LENGTH_SHORT).show()
                        } else {
                            val roleLabel = when {
                                isHod -> "HOD"
                                isCR -> "Class Representative (CR)"
                                isLR -> "Ladies Representative (LR)"
                                else -> currentUser?.role ?: "Faculty"
                            }
                            val newNotice = ClassroomNoticeMessage(
                                id = noticeMessages.size + 1,
                                title = newMsgTitle.trim(),
                                content = newMsgContent.trim(),
                                author = currentUser?.name ?: "Representative",
                                role = roleLabel,
                                timeAgo = "Just now",
                                tag = newMsgTag
                            )
                            noticeMessages = listOf(newNotice) + noticeMessages
                            showPostMessageDialog = false
                            newMsgTitle = ""
                            newMsgContent = ""
                            Toast.makeText(context, "Classroom Message Posted!", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Post")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPostMessageDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

private fun String?.isNull_or_empty(): Boolean = this.isNullOrEmpty()
