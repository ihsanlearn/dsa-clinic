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

        Doctor session = FileManager.loadSession(SESSION_FILE);
        if (session != null) loginList.loginDoctor(session);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Address: ");
                    String addr = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    int id = (int)(Math.random() * 9000) + 1000;
                    Patient p = new Patient(id, name, age, addr, phone);
                    patientList.addPatient(p);
                    System.out.println("Patient added with ID: " + id);
                }
                case "2" -> {
                    System.out.print("Patient ID to remove: ");
                    int id = Integer.parseInt(sc.nextLine());
                    if (patientList.removePatientById(id)) {
                        System.out.println("Removed.");
                    } else System.out.println("Not found.");
                }
                case "3" -> {
                    System.out.print("Search by name: ");
                    String name = sc.nextLine();
                    Patient found = patientList.findPatientByName(name);
                    if (found != null)
                        System.out.println(found.id + " | " + found.name + " | " + found.age);
                    else System.out.println("Not found.");
                }
                case "4" -> patientList.displayAllPatients();
                case "5" -> {
                    System.out.println("1. Signup\n2. Login");
                    String sub = sc.nextLine();
                    if (sub.equals("1")) {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Spec: ");
                        String spec = sc.nextLine();
                        System.out.print("Pass: ");
                        String pass = sc.nextLine();
                        Doctor d = new Doctor(id, name, spec, pass);
                        allDoctors[doctorCount++] = d;
                        FileManager.saveDoctors(DOCTOR_FILE, allDoctors, doctorCount);
                        System.out.println("Signed up.");
                    } else {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Pass: ");
                        String pass = sc.nextLine();
                        boolean ok = false;
                        for (int i = 0; i < doctorCount; i++) {
                            if (allDoctors[i].id == id && allDoctors[i].password.equals(pass)) {
                                String time = new SimpleDateFormat("HH:mm").format(new Date());
                                allDoctors[i].loginTime = time;
                                loginList.loginDoctor(allDoctors[i]);
                                FileManager.saveSession(allDoctors[i], SESSION_FILE);
                                System.out.println("Login success.");
                                ok = true;
                                break;
                            }
                        }
                        if (!ok) System.out.println("Login failed.");
                    }
                }
                case "6" -> {
                    Doctor d = loginList.getLoggedInDoctor();
                    if (d != null) {
                        loginList.logoutDoctor(d.id);
                        FileManager.clearSession(SESSION_FILE);
                        System.out.println("Logged out.");
                    } else System.out.println("No doctor is logged in.");
                }
                case "7" -> {
                    Doctor d = loginList.getLoggedInDoctor();
                    if (d != null)
                        System.out.println("Logged in doctor: " + d.name + " | " + d.loginTime);
                    else
                        System.out.println("No doctor is currently logged in.");
                }
                case "8" -> {
                    Doctor d = loginList.getLoggedInDoctor();
                    if (d == null) {
                        System.out.println("Login required.");
                        break;
                    }
                    System.out.print("Patient ID: ");
                    int pid = Integer.parseInt(sc.nextLine());
                    System.out.print("Time (HH:mm): ");
                    String time = sc.nextLine();
                    int id = (int)(Math.random() * 9000) + 1000;
                    Appointment a = new Appointment(id, pid, d.id, time);
                    appointmentMap.getQueue(d.id).enqueue(a);
                    System.out.println("Scheduled.");
                }
                case "9" -> {
                    Doctor d = loginList.getLoggedInDoctor();
                    if (d == null) {
                        System.out.println("Login required.");
                        break;
                    }
                    Appointment a = appointmentMap.getQueue(d.id).dequeue();
                    if (a == null) System.out.println("No appointments.");
                    else System.out.println("Processing appointment: " + a.id);
                }
                case "10" -> {
                    Doctor d = loginList.getLoggedInDoctor();
                    if (d == null) {
                        System.out.println("Login required.");
                        break;
                    }
                    appointmentMap.getQueue(d.id).display();
                }
                case "11" -> {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Patient p = patientBST.search(id);
                    if (p != null)
                        System.out.println("Found: " + p.name);
                    else
                        System.out.println("Not found.");
                }
                case "12" -> {
                    patientBST.reset();
                    Patient[] arr = patientList.toArray();
                    for (int i = 0; i < arr.length; i++) patientBST.insert(arr[i]);
                    patientBST.inOrderDisplay();
                }
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
        System.out.print("Choose: ");
    }
}
