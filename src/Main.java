import structures.*;
import utils.*;

import java.util.Scanner;

public class Main {
    static LinkedList patientList = new LinkedList();
    static LinkedList doctorList = new LinkedList();
    static BST patientTree = new BST();
    static AppointmentMap appointmentMap = new AppointmentMap();

    public static void main(String[] args) {
        FileManager.loadAll(patientList, doctorList, patientTree, appointmentMap);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("==== Daisuke Clinic ====");
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
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> patientList.addFromInput(sc);
                case 2 -> patientList.removeByIdInput(sc);
                case 3 -> patientList.findByNameInput(sc);
                case 4 -> patientList.display();
                case 5 -> doctorList.loginDoctor(sc);
                case 6 -> doctorList.logoutDoctor(sc);
                case 7 -> doctorList.viewLastLogin();
                case 8 -> appointmentMap.schedule(sc, patientList, doctorList);
                case 9 -> appointmentMap.process(sc, doctorList);
                case 10 -> appointmentMap.display(sc);
                case 11 -> patientTree.searchInput(sc);
                case 12 -> patientTree.inOrder();
                case 0 -> System.out.println("Saving & exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        FileManager.saveAll(patientList, doctorList, patientTree, appointmentMap);
    }
}
