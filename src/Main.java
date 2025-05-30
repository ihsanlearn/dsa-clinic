import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LinkedList patientList = new LinkedList();
        DoctorLinkedList doctorList = new DoctorLinkedList();
        Queue appointmentQueue = new Queue();
        BST patientTree = new BST();

        while (true) {
            System.out.println("--- Data Manager Pro ---");
            System.out.println("1. Add New Patient");
            System.out.println("2. Remove Patient by ID");
            System.out.println("3. Search Patient by Name");
            System.out.println("4. Display All Patients");
            System.out.println("5. Doctor Login");
            System.out.println("6. Doctor Logout");
            System.out.println("7. View Last Logged-in Doctor");
            System.out.println("8. Schedule Appointment");
            System.out.println("9. Process Appointment");
            System.out.println("10. Display Upcoming Appointments");
            System.out.println("11. Search Patient by ID (BST)");
            System.out.println("12. Display All Patients (BST Inorder)");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("enter id : "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("name     : "); String name = sc.nextLine();
                    System.out.print("age      : "); int age = sc.nextInt(); sc.nextLine();
                    System.out.print("address  : "); String address = sc.nextLine();
                    System.out.print("phone    : "); String phone = sc.nextLine();
                    Patient p = new Patient(id, name, age, address, phone);
                    patientList.addPatient(p);
                    patientTree.insertPatient(p);
                    break;
                case 2:
                    System.out.print("ID to remove  : ");
                    patientList.removePatientById(sc.nextInt());
                    break;
                case 3:
                    System.out.print("name to search  : ");
                    Patient found = patientList.findPatientByName(sc.nextLine());
                    System.out.println(found != null ? found : "Patient not found.");
                    break;
                case 4:
                    patientList.displayAllPatients();
                    break;
                case 5:
                    System.out.print("doctod id  : "); int did = sc.nextInt(); sc.nextLine();
                    System.out.print("name       : "); String dname = sc.nextLine();
                    System.out.print("specialty  : "); String spec = sc.nextLine();
                    System.out.print("login time : "); String login = sc.nextLine();
                    doctorList.loginDoctor(new Doctor(did, dname, spec, login));
                    break;
                case 6:
                    System.out.print("Enter Doctor ID to logout: ");
                    doctorList.logoutDoctor(sc.nextInt());
                    break;
                case 7:
                    doctorList.getLastLoggedInDoctor();
                    break;
                case 8:
                    System.out.print("appointment id : "); int aid = sc.nextInt();
                    System.out.print("patient id     : "); int pid = sc.nextInt();
                    System.out.print("doctor id      : "); int apdid = sc.nextInt(); sc.nextLine();
                    System.out.print("time           : "); String time = sc.nextLine();
                    appointmentQueue.scheduleAppointment(new Appointment(aid, pid, apdid, time));
                    break;
                case 9:
                    appointmentQueue.processNextAppointment();
                    break;
                case 10:
                    appointmentQueue.viewUpcomingAppointments();
                    break;
                case 11:
                    System.out.print("patient id to search  : ");
                    Patient result = patientTree.searchPatient(sc.nextInt());
                    System.out.println(result != null ? result : "Not found!");
                    break;
                case 12:
                    patientTree.inOrderDisplay();
                    break;
                case 0:
                    return;
            }
        }
    }
}
