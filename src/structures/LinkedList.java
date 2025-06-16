package structures;

import models.*;
import utils.*;

import java.util.Scanner;

public class LinkedList {
    private Node<Object> head;

    public void addFromInput(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        Patient p = new Patient(IdGenerator.getNextPatientId(), name, age, address, phone);
        insert(p);
        System.out.println("Patient added.");
    }

    public void insert(Object data) {
        Node<Object> newNode = new Node<>(data);
        if (head == null) head = newNode;
        else {
            Node<Object> temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    public void removeByIdInput(Scanner sc) {
        System.out.print("Enter Patient ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        removeById(id);
    }

    public void removeById(int id) {
        Node<Object> temp = head, prev = null;
        while (temp != null) {
            if (temp.data instanceof Patient p && p.id == id) {
                if (prev == null) head = temp.next;
                else prev.next = temp.next;
                System.out.println("Patient removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("Patient not found.");
    }

    public void findByNameInput(Scanner sc) {
        System.out.print("Search by name: ");
        String name = sc.nextLine().toLowerCase();
        Node<Object> temp = head;
        while (temp != null) {
            if (temp.data instanceof Patient p && p.name.toLowerCase().contains(name)) {
                System.out.println("Found: " + p.name + " (" + p.id + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Patient not found.");
    }

    public Object findDoctorByUsername(String name) {
        Node<Object> temp = head;
        while (temp != null) {
            if (temp.data instanceof Doctor d && d.name.equals(name)) return d;
            temp = temp.next;
        }
        return null;
    }

    public void loginDoctor(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        Doctor d = (Doctor) findDoctorByUsername(name);

        if (d == null) {
            System.out.print("Specialty: ");
            String spec = sc.nextLine();
            System.out.print("Password: ");
            String pw = sc.nextLine();
            d = new Doctor(IdGenerator.getNextDoctorId(), name, spec, pw, IdGenerator.getCurrentTime());
            insert(d);
            System.out.println("Signup & Login Success.");
        } else {
            System.out.print("Password: ");
            String pw = sc.nextLine();
            if (!d.password.equals(pw)) {
                System.out.println("Wrong password.");
                return;
            }
            d.status = IdGenerator.getCurrentTime();
            System.out.println("Login success at " + d.status);
        }
    }

    public void logoutDoctor(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        Doctor d = (Doctor) findDoctorByUsername(name);
        if (d != null && !d.status.equals("inactive")) {
            d.status = "inactive";
            System.out.println("Logout success.");
        } else {
            System.out.println("Doctor not found or already inactive.");
        }
    }

    public void viewLastLogin() {
        String latest = "";
        Doctor recent = null;
        Node<Object> temp = head;
        while (temp != null) {
            if (temp.data instanceof Doctor d && !d.status.equals("inactive")) {
                if (latest.compareTo(d.status) < 0) {
                    latest = d.status;
                    recent = d;
                }
            }
            temp = temp.next;
        }

        if (recent != null) {
            System.out.println("Last login: " + recent.name + " at " + recent.status);
        } else {
            System.out.println("No active doctor.");
        }
    }

    public void display() {
        Node<Object> temp = head;
        while (temp != null) {
            if (temp.data instanceof Patient p) {
                System.out.println(p.id + " - " + p.name + ", " + p.age + " yo, " + p.phone);
            }
            temp = temp.next;
        }
    }

    public Node<Object> getHead() {
        return head;
    }
}
