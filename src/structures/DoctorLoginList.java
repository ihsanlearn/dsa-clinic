package structures;

import models.Doctor;

public class DoctorLoginList {

    class Node {
        Doctor doctor;
        Node next;

        Node(Doctor doctor) {
            this.doctor = doctor;
        }
    }

    private Node head = null;

    public void loginDoctor(Doctor doctor) {
        Node newNode = new Node(doctor);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }


    public void logoutDoctor(int id) {
        if (head == null) return;
        if (head.doctor.id == id) {
            head = head.next;
            return;
        }
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (curr.doctor.id == id) {
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public Doctor getLastLoggedInDoctor() {
        return (head != null) ? head.doctor : null;
    }

    public void displayLoggedInDoctors() {
        Node tmp = head;
        while (tmp != null) {
            Doctor d = tmp.doctor;
            System.out.println(d.id + " | " + d.name + " | " + d.specialty + " | Login Time: " + d.loginTime);
            tmp = tmp.next;
        }
    }

    public boolean isLoggedIn(int doctorId) {
        Node tmp = head;
        while (tmp != null) {
            if (tmp.doctor.id == doctorId) return true;
            tmp = tmp.next;
        }
        return false;
    }

    public Doctor isLoggedIn(String name) {
        Node temp = head;
        while (head != null) {
            if (temp.doctor.name.trim().equalsIgnoreCase(name)) return temp.doctor;
            temp = temp.next;
        }
        return null;
    }
}
