## Submission File
  - `Appointment.java`
  - `BST.java`
  - `Doctor.java`
  - `LinkedList.java`
  - `Main.java`
  - `Patient.java`
  - `Queue.java`
  - `Stack.java`




## Patient Record Management (singly linkedlist)
# Patient 
  - `ID`
  - `Name`
  - `Age`
  - `Address`
  - `Phone Number`

# Method 
  - `addPatient()`
  - `removePatientByID(int id)`
  - `findPatientByName(String name)`
  - `displayAllPatient()`

# How it works
1. 


## Doctor Login List (singly or doubly linkedlist)
# Doctor
  - `ID`
  - `Name`
  - `Specialty`
  - `Login Time`

# Method
  - `loginDoctor(Doctor doctor)` -> add to list
  - `logoutDoctor(int doctorID)` -> remove by ID
  - `getAllLogedInDoctors()`     -> traverse and display



## Appointment Queue
# each Appointment contains
  - `AppointmentID`
  - `PatientID`
  - `DoctorID`
  - `Time`

# Method
  - `scheduleAppointment(Appointment a)`
  - `processNextAppointment()`
  - `viewUpcomingAppointment()`



## Searchable Patient Tree (Binary Search Tree)
  Use PatientID as the key

# Method
 - `insertPatient(Patient p)`
 - `searchPatientByID(int id)`
 - `inOrderDisplay`