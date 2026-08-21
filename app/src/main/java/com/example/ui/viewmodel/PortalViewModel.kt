package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.database.AppDatabase
import com.example.data.model.*
import com.example.data.repository.AppRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PortalViewModel(
    application: Application,
    private val repository: AppRepository
) : AndroidViewModel(application) {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError.asStateFlow()

    private var notificationsListenerRegistration: com.google.firebase.firestore.ListenerRegistration? = null
    private var chatListenerRegistration: com.google.firebase.firestore.ListenerRegistration? = null

    init {
        viewModelScope.launch {
            repository.getAllUsers().first().find { it.isLoggedIn }?.let {
                _currentUser.value = it
                setupRealtimeListeners(it)
            }
        }
        autoExpireOutdatedRequests()
    }

    private fun autoExpireOutdatedRequests() {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            repository.getAllOutpasses().first().filter { it.status.startsWith("PENDING") }.forEach {
                if (it.timestamp < (now - 172800000)) {
                    repository.rejectOutpass(it, "SYSTEM", "Auto-expired")
                }
            }
        }
    }

    private fun setupRealtimeListeners(user: User) {
        setupRealtimeNotificationsListener(user)
        setupRealtimeChatListener()
    }

    private fun setupRealtimeChatListener() {
        chatListenerRegistration?.remove()
        try {
            val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            chatListenerRegistration = db.collection("chat_messages")
                .addSnapshotListener { snapshots, _ ->
                    if (snapshots != null && !snapshots.isEmpty) {
                        viewModelScope.launch {
                            for (doc in snapshots.documentChanges) {
                                if (doc.type == com.google.firebase.firestore.DocumentChange.Type.ADDED) {
                                    doc.document.toObject(ChatMessage::class.java)?.let { repository.insertRawChatMessage(it) }
                                }
                            }
                        }
                    }
                }
        } catch (e: Exception) {}
    }

    private fun setupRealtimeNotificationsListener(user: User) {
        notificationsListenerRegistration?.remove()
        try {
            val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            notificationsListenerRegistration = db.collection("notifications")
                .whereIn("targetStudentId", listOf("ALL", user.userId, user.rollNumber))
                .addSnapshotListener { snapshots, _ ->
                    if (snapshots != null && !snapshots.isEmpty) {
                        viewModelScope.launch {
                            for (doc in snapshots.documentChanges) {
                                if (doc.type == com.google.firebase.firestore.DocumentChange.Type.ADDED) {
                                    doc.document.toObject(CollegeNotification::class.java)?.let { repository.insertRawNotification(it) }
                                }
                            }
                        }
                    }
                }
        } catch (e: Exception) {}
    }

    override fun onCleared() {
        super.onCleared()
        notificationsListenerRegistration?.remove()
        chatListenerRegistration?.remove()
    }

    val isFirebaseConnected: StateFlow<Boolean> = flow {
        emit(try { com.google.firebase.FirebaseApp.getApps(application).isNotEmpty() } catch (e: Exception) { false })
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val allUsers = repository.getAllUsers().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allOutpasses = repository.getAllOutpasses().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val studentOutpasses = _currentUser.filterNotNull().flatMapLatest { repository.getOutpassesForStudent(it.userId) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allCertificates = repository.getAllCertificateRequests().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val studentCertificates = _currentUser.filterNotNull().flatMapLatest { repository.getCertificatesForStudent(it.userId) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val stationeryItems = repository.getAllStationeryItems().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allStationeryRequests = repository.getAllStationeryRequests().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val studentStationeryRequests = _currentUser.filterNotNull().flatMapLatest { repository.getStationeryRequestsForStudent(it.userId) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allPrintRequests = repository.getAllPrintRequests().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val studentPrintRequests = _currentUser.filterNotNull().flatMapLatest { repository.getPrintRequestsForStudent(it.userId) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val canteenItems = repository.getAllCanteenItems().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allCanteenBookings = repository.getAllCanteenBookings().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val studentCanteenBookings = _currentUser.filterNotNull().flatMapLatest { repository.getCanteenBookingsForStudent(it.userId) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val notifications = _currentUser.filterNotNull().flatMapLatest { repository.getNotificationsForStudent(it.userId) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val chatMessages = repository.getAllChatMessages().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allEvents = repository.getAllEvents().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun createEvent(title: String, description: String, date: String, time: String, venue: String, dpt: String, organizerRole: String = "ADMIN") {
        viewModelScope.launch { repository.insertEvent(CollegeEvent(title = title, description = description, date = date, time = time, venue = venue, organizerRole = organizerRole, filterDepartment = dpt)) }
    }
    fun updateEvent(event: CollegeEvent) { viewModelScope.launch { repository.updateEvent(event) } }
    fun deleteEvent(event: CollegeEvent) { viewModelScope.launch { repository.deleteEvent(event) } }

    private val _canteenCart = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val canteenCart = _canteenCart.asStateFlow()
    fun addToCanteenCart(itemId: Int) { _canteenCart.value = _canteenCart.value.toMutableMap().apply { this[itemId] = (get(itemId) ?: 0) + 1 } }
    fun removeFromCanteenCart(itemId: Int) { _canteenCart.value = _canteenCart.value.toMutableMap().apply { val c = get(itemId) ?: 0; if (c > 1) this[itemId] = c - 1 else remove(itemId) } }
    fun clearCanteenCart() { _canteenCart.value = emptyMap() }

    fun login(email: String, pass: String, autoLogin: Boolean = false) {
        viewModelScope.launch {
            val user = repository.getAllUsers().first().find { it.email.equals(email, ignoreCase = true) }
            if (user == null) { _loginError.value = "User not found."; return@launch }
            if (user.password != pass) { _loginError.value = "Invalid password."; return@launch }
            if (user.isPaused) { _loginError.value = "Account suspended."; return@launch }
            val updated = user.copy(isLoggedIn = true); repository.updateUser(updated); _currentUser.value = updated; setupRealtimeListeners(updated)
        }
    }
    fun logout() {
        viewModelScope.launch { _currentUser.value?.let { repository.updateUser(it.copy(isLoggedIn = false)) }; _currentUser.value = null; notificationsListenerRegistration?.remove(); chatListenerRegistration?.remove() }
    }

    fun updateUserProfile(name: String, email: String, phone: String, parentContact: String, department: String) {
        val user = _currentUser.value ?: return
        viewModelScope.launch {
            val updated = user.copy(name = name, email = email, phone = phone, parentContact = parentContact, department = department)
            repository.updateUser(updated); _currentUser.value = updated
        }
    }

    fun registerNewStudent(userId: String, name: String, roll: String, dept: String) {
        viewModelScope.launch { repository.insertUser(User(userId = userId, name = name, rollNumber = roll, department = dept, email = "$roll@college.edu", phone = "", parentContact = "", role = "STUDENT")) }
    }
    fun registerNewUser(userId: String, name: String, roll: String, dept: String, email: String, role: String, password: String, autoLogin: Boolean = false) {
        viewModelScope.launch { repository.insertUser(User(userId = userId, name = name, rollNumber = roll, department = dept, email = email, phone = "", parentContact = "", role = role, password = password)) }
    }
    fun saveUser(user: User) { viewModelScope.launch { repository.insertUser(user) } }
    fun deleteUser(user: User) { viewModelScope.launch { repository.deleteUser(user) } }
    fun updateUserAdmin(user: User) { viewModelScope.launch { repository.updateUser(user) } }

    fun submitOutpass(dateTime: String, reason: String, expectedReturnTime: String) {
        val user = _currentUser.value ?: return
        viewModelScope.launch { repository.createOutpass(user.userId, user.name, user.rollNumber, user.department, dateTime, reason, expectedReturnTime, user.parentContact) }
    }
    fun actionOnOutpass(request: OutpassRequest, approve: Boolean, comment: String = "") {
        viewModelScope.launch { if (approve) repository.approveOutpass(request, "ADMIN") else repository.rejectOutpass(request, "ADMIN", comment) }
    }

    fun submitCertificateRequest(certType: String, purpose: String) {
        val user = _currentUser.value ?: return
        viewModelScope.launch { repository.createCertificate(user.userId, user.name, user.rollNumber, user.department, certType, purpose) }
    }
    fun actionOnCertificate(request: CertificateRequest, approve: Boolean) {
        viewModelScope.launch { if (approve) repository.approveCertificate(request, "ADMIN") else repository.rejectCertificate(request, "ADMIN") }
    }

    fun purchaseStationeryItem(item: StationeryItem, quantity: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val user = _currentUser.value ?: return
        if (item.stock < quantity) { onError("Out of stock"); return }
        viewModelScope.launch { if (repository.placeStationeryOrder(user.userId, user.name, item.id, item.name, quantity, item.price * quantity)) onSuccess() else onError("Order failed") }
    }
    fun changeStationeryRequestStatus(request: StationeryRequest, status: String) { viewModelScope.launch { repository.completeStationeryRequest(request, status) } }
    fun addStationeryStock(itemId: String, name: String, additionalStock: Int, category: String, price: Double, imageUrl: String = "") {
        viewModelScope.launch { repository.updateStationeryItem(StationeryItem(itemId, name, additionalStock, category, price)) }
    }

    fun submitPrintRequest(fileName: String, pages: Int, printType: String, copyType: String, bindingType: String, docUrl: String = "") {
        val user = _currentUser.value ?: return
        viewModelScope.launch { repository.createPrintRequest(user.userId, user.name, fileName, pages, printType, copyType, bindingType) }
    }
    fun actionOnPrintRequest(request: PrintRequest, status: String) { viewModelScope.launch { repository.updatePrintStatus(request, status) } }
    fun actionOnMultiplePrintRequests(requests: List<PrintRequest>, status: String) { viewModelScope.launch { requests.forEach { repository.updatePrintStatus(it, status) } } }

    fun bookCanteenCart(onSuccess: (CanteenBooking) -> Unit) {
        val user = _currentUser.value ?: return
        viewModelScope.launch {
            val items = repository.getAllCanteenItems().first()
            var total = 0.0; val summary = StringBuilder()
            _canteenCart.value.forEach { (id, qty) -> items.find { it.id == id }?.let { total += it.price * qty; summary.append("${it.name} x$qty, ") } }
            val booking = repository.placeCanteenBooking(user.userId, user.name, summary.toString().removeSuffix(", "), total)
            clearCanteenCart(); onSuccess(booking)
        }
    }
    fun actionOnCanteenBooking(booking: CanteenBooking, status: String) { viewModelScope.launch { repository.completeCanteenBooking(booking, status) } }
    fun saveCanteenItem(item: CanteenItem) { viewModelScope.launch { repository.updateCanteenItem(item) } }
    fun deleteCanteenItem(item: CanteenItem) { viewModelScope.launch { repository.deleteCanteenItem(item) } }

    fun markAllNotificationsRead() { _currentUser.value?.let { viewModelScope.launch { repository.markAllNotificationsAsRead(it.userId) } } }
    fun markNotificationRead(notifId: Int) { viewModelScope.launch { repository.markNotificationAsRead(notifId) } }
    fun broadcastNotification(title: String, content: String, target: String) { viewModelScope.launch { repository.createNotification(target, title, content, "General") } }
    fun verifyExitSecurity(request: OutpassRequest) { viewModelScope.launch { repository.verifyOutpassExit(request) } }

    fun sendChatMessage(messageText: String, senderName: String = "Admin", isStaff: Boolean = true, recipientRole: String = "", isSheet: Boolean = false, sheetData: String? = null) {
        val user = _currentUser.value ?: return
        viewModelScope.launch { repository.sendChatMessage(ChatMessage(senderId = user.userId, senderName = senderName, messageText = messageText, recipientRole = recipientRole, isSheetAttachment = isSheet, attachmentData = sheetData)) }
    }

    private val _firebaseTestState = MutableStateFlow("IDLE")
    val firebaseTestState = _firebaseTestState.asStateFlow()
    private val _firestorePullStatus = MutableStateFlow("")
    val firestorePullStatus = _firestorePullStatus.asStateFlow()
    fun cleanCacheAndPullFirestore() { viewModelScope.launch { _firestorePullStatus.value = repository.pullFromFirestoreAndCleanCache("all") } }
    fun testFirebaseWrite() { viewModelScope.launch { _firebaseTestState.value = "TESTING"; try { com.google.firebase.firestore.FirebaseFirestore.getInstance().collection("test").add(mapOf("t" to 1)).addOnSuccessListener { _firebaseTestState.value = "SUCCESS" } } catch (e: Exception) { _firebaseTestState.value = "ERROR" } } }
    private val _firebaseSyncProgress = MutableStateFlow("")
    val firebaseSyncProgress = _firebaseSyncProgress.asStateFlow()
    fun bulkSyncToFirebase(onComplete: (String) -> Unit) { viewModelScope.launch { repository.forceBulkSyncToCloud(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()); onComplete("Done") } }

    private val _departments = MutableStateFlow(listOf(Department("CSE", "CSE", emptyList(), "Dept of CSE")))
    val departments = _departments.asStateFlow()
    fun addDepartment(name: String, description: String, facName: String, facRole: String, facSpec: String, facEmail: String) {
        _departments.value += Department(name, name, listOf(Faculty(facName, facName, facRole, facSpec, facEmail)), description)
    }
    fun addFacultyToDepartment(deptId: String, name: String, designation: String, email: String, specialization: String, phone: String) {
        _departments.value = _departments.value.map { if (it.id == deptId) it.copy(faculties = it.faculties + Faculty(name, name, designation, specialization, email)) else it }
    }

    private val _placementDrives = MutableStateFlow<List<PlacementDrive>>(emptyList())
    val placementDrives = _placementDrives.asStateFlow()
    private val _placementApplications = MutableStateFlow<List<PlacementApplication>>(emptyList())
    val placementApplications = _placementApplications.asStateFlow()
    private val _studentCgpa = MutableStateFlow<Map<String, Double>>(emptyMap())
    val studentCgpa = _studentCgpa.asStateFlow()
    fun updateStudentCgpa(cgpa: Double) { _currentUser.value?.let { _studentCgpa.value += (it.userId to cgpa) } }
    fun postPlacementDrive(company: String, role: String, ctc: String, cgpa: Double, branches: String, desc: String, deadlineStr: String) {
        _placementDrives.value += PlacementDrive(0, company, role, ctc, cgpa, deadlineStr, "Main Campus", "Full Time", "Active", desc, branches)
    }
    fun applyToPlacementDrive(driveId: Int, resumeUrl: String) {
        val user = _currentUser.value ?: return
        _placementApplications.value += PlacementApplication(0, driveId, user.userId, user.name, user.department, studentCgpa.value[user.userId] ?: 0.0, "PENDING", resumeUrl)
    }
    fun updateApplicationStatus(appId: Int, status: String, feedback: String) {}
    fun assignStudentDesignation(studentId: String, designation: String?) { viewModelScope.launch { repository.getAllUsers().first().find { it.userId == studentId }?.let { repository.updateUser(it.copy(studentDesignation = designation)) } } }

    val tpcStaffList = MutableStateFlow(emptyList<TpcStaff>()).asStateFlow()
}

data class PlacementDrive(val id: Int, val companyName: String, val roleName: String, val packageCTC: String, val eligibilityCGPA: Double, val deadline: String, val location: String, val jobType: String, val status: String, val description: String, val eligibleBranches: String)
data class PlacementApplication(val id: Int, val driveId: Int, val studentId: String, val studentName: String, val department: String, val cgpa: Double, val status: String, val resumeUrl: String = "", val feedback: String = "", val companyName: String = "", val roleName: String = "")
data class Department(val id: String, val name: String, val faculties: List<Faculty>, val description: String)
data class Faculty(val id: String, val name: String, val designation: String, val specialization: String, val email: String)
data class TpcStaff(val id: String, val name: String, val designation: String, val responsibility: String, val email: String)

class PortalViewModelFactory(private val application: Application, private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortalViewModel::class.java)) return PortalViewModel(application, repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
