// package src;

import models.*;
import structures.*;
import utils.FileManager;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static final String PATIENT_FILE = "src/data/patients.txt";
    static final String DOCTOR_FILE = "src/data/doctors.txt";
    static final String APPOINTMENT_FILE = "src/data/appointments.txt";
    static final String SESSION_FILE = "src/data/session.txt";

    static PatientLinkedList patientList = new PatientLinkedList();
    static PatientBST patientBST = new PatientBST();
    static AppointmentMap appointmentMap = new AppointmentMap();
    static DoctorLoginList loginList = new DoctorLoginList();
    static Doctor[] allDoctors = new Doctor[100];
    static int doctorCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FileManager.loadPatients(patientList, PATIENT_FILE);
        FileManager.loadAppointments(appointmentMap, APPOINTMENT_FILE);
        doctorCount = FileManager.loadDoctors(DOCTOR_FILE, allDoctors);

        FileManager.loadSession(loginList, DOCTOR_FILE);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine();

            switch (choice) {
                // ADD NEW PATIENT
                case "1" -> {
                    System.out.print("Name    -> ");
                    String name = sc.nextLine();
                    System.out.print("Age     -> ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Address ->  ");
                    String addr = sc.nextLine();
                    System.out.print("Phone   -> ");
                    String phone = sc.nextLine();
                    int id = FileManager.generateNewId(PATIENT_FILE);

                    patientList.addPatient(new Patient(id, name, age, addr, phone));
                    System.out.println("Patient added with ID " + id);
                }
                // REMOVE PATIENT BY ID
                case "2" -> {
                    System.out.print("Patient ID to remove ->  ");
                    int id = Integer.parseInt(sc.nextLine());
                    if (patientList.removePatientById(id)) {
                        System.out.println("Removed!");
                    } else System.out.println("Not found!");
                }
                // SEARCH PATIENT BY NAME
                case "3" -> {
                    System.out.print("Search by name -> ");
                    String name = sc.nextLine();
                    Patient found = patientList.findPatientByName(name);
                    if (found != null)
                        System.out.println(found.id + " | " + found.name + " | " + found.age);
                    else System.out.println("Not found!");
                }
                // DISPLAY ALL PATIENT
                case "4" -> patientList.displayAllPatients();
                // DOCTOR DIGNUP/LOGIN
                case "5" -> {
                    System.out.println("1. Signup\n2. Login");
                    String sub = sc.nextLine();
                    if (sub.equals("1")) {
                        System.out.print("Name      -> ");
                        String name = sc.nextLine();
                        System.out.print("Specialty -> ");
                        String spec = sc.nextLine();
                        System.out.print("Password  -> ");
                        String pass = sc.nextLine();
                        Doctor d = new Doctor(FileManager.generateNewId(DOCTOR_FILE), name, spec, pass);
                        allDoctors[doctorCount++] = d;
                        FileManager.saveDoctors(DOCTOR_FILE, allDoctors, doctorCount);
                        System.out.println("Signed up!");   
                    } else {
                        System.out.print("ID       -> ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Password -> ");
                        String pass = sc.nextLine();
                        boolean ok = false;
                        for (int i = 0; i < doctorCount; i++) {
                            if (allDoctors[i].id == id && allDoctors[i].password.equals(pass)) {
                                String time = new SimpleDateFormat("HH:mm").format(new Date());
                                allDoctors[i].loginTime = time;
                                loginList.loginDoctor(allDoctors[i]);
                                FileManager.updateLogin(allDoctors[i], DOCTOR_FILE);
                                System.out.println("Login success.");
                                ok = true;
                                break;
                            }
                        }
                        if (!ok) System.out.println("Login failed!");
                    }
                }
                // DOCTOR LOG OUT
                case "6" -> {
                    System.out.print("ID       -> ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Password -> ");
                    String pass = sc.nextLine();
                    boolean ok = false;
                    for (int i = 0; i < doctorCount; i++) {
                        if (allDoctors[i].id == id && allDoctors[i].password.equals(pass)) {
                            System.out.println("tkan");
                            if (allDoctors[i].loginTime.trim().equalsIgnoreCase("inactive")) break;
                            System.out.println("rtekan");
                            allDoctors[i].loginTime = "inactive";
                            loginList.logoutDoctor(id);
                            FileManager.updateLogout(allDoctors[i], DOCTOR_FILE);
                            System.out.println("Logout success.");
                            ok = true;
                            break;
                        }
                    }
                    if (!ok) System.out.println("Logout failed!");
                }
                // VIEW LAST LOGGED IN DOCTORS
                case "7" -> {
                    Doctor d = loginList.getLastLoggedInDoctor(); //////
                    if (d != null)
                        System.out.println("Logged in doctor: " + d.name + " | " + d.loginTime);
                    else
                        System.out.println("No doctor is currently logged in.");
                }
                // SCHEDULE APPOINTMENT
                case "8" -> {
                    System.out.print("Patient ID: ");
                    int pid = Integer.parseInt(sc.nextLine());
                    System.out.print("Doctor ID: ");
                    int did = Integer.parseInt(sc.nextLine());
                    System.out.print("Time (HH:mm): ");
                    String time = sc.nextLine();
                    int id = FileManager.generateNewId(APPOINTMENT_FILE);
                    Appointment a = new Appointment(id, pid, did, time);
                    appointmentMap.getQueue(did).enqueue(a);
                    System.out.println("Scheduled.");
                }
                // PROCESS APPOINTMENT
                case "9" -> {
                    System.out.print("Name -> ");
                    String name = sc.nextLine();

                    Doctor d = loginList.isLoggedIn(name);
                    if (d == null) {
                        System.out.println("Login required.");
                        break;
                    }
                    Appointment a = appointmentMap.getQueue(d.id).dequeue();
                    if (a == null) System.out.println("No appointments.");
                    else System.out.println("Processing appointment: " + a.id);
                }
                // DISPLAY UPCOMING APPOINTMENT
                case "10" -> {
                    System.out.print("Name -> ");
                    String name = sc.nextLine();
                    
                    Doctor d = loginList.isLoggedIn(name);
                    if (d == null) {
                        System.out.println("Login required.");
                        break;
                    }
                    appointmentMap.getQueue(d.id).display();
                }
                // SEARCH PATIENT BY ID (BST)
                case "11" -> {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Patient p = patientBST.search(id);
                    if (p != null)
                        System.out.println("Found: " + p.name);
                    else
                        System.out.println("Not found.");
                }
                // DISPLAY ALL PATIENT (BST IN-ORDER)
                case "12" -> {
                    patientBST.reset();
                    Patient[] arr = patientList.toArray();
                    for (int i = 0; i < arr.length; i++) patientBST.insert(arr[i]);
                    patientBST.inOrderDisplay();
                }
                // EXIT
                case "0" -> {
                    FileManager.savePatients(patientList, PATIENT_FILE);
                    FileManager.saveAppointments(appointmentMap, APPOINTMENT_FILE);
                    running = false;
                }
                default -> System.out.println("Invalid.");
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n==== Daisuke Clinic ====");
        System.out.println("1. Add New Patient");
        System.out.println("2. Remove Patient by ID");
        System.out.println("3. Search Patient by Name");
        System.out.println("4. Display All Patients");
        System.out.println("5. Doctor Signup/Login");
        System.out.println("6. Doctor Logout");
        System.out.println("7. View Last Logged-in Doctor");
        System.out.println("8. Schedule Appointment");
        System.out.println("9. Process Appointment");
        System.out.println("10. Display Upcoming Appointments");
        System.out.println("11. Search Patient by ID (BST)");
        System.out.println("12. Display All Patients (BST Inorder)");
        System.out.println("0. Exit");
        System.out.print("Choose -> ");
    }
}
