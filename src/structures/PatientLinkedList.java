package structures;

import models.Patient;

public class PatientLinkedList {

    class Node {
        Patient data;
        Node next;

        Node(Patient data) {
            this.data = data;
        }
    }

    private Node head = null;

    public void addPatient(Patient patient) {
        Node newNode = new Node(patient);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
    }

    public boolean removePatientById(int id) {
        if (head == null) return false;
        if (head.data.id == id) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.id == id) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Patient findPatientByName(String name) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.name.equalsIgnoreCase(name)) return temp.data;
            temp = temp.next;
        }
        return null;
    }

    public void displayAllPatients() {
        Node temp = head;
        while (temp != null) {
            Patient p = temp.data;
            System.out.println(p.id + " | " + p.name + " | " + p.age + " | " + p.address + " | " + p.phone);
            temp = temp.next;
        }
    }

    public Patient[] toArray() {
        int count = 0;
        Node tmp = head;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }

        Patient[] arr = new Patient[count];
        tmp = head;
        int i = 0;
        while (tmp != null) {
            arr[i++] = tmp.data;
            tmp = tmp.next;
        }
        return arr;
    }

    public void clear() {
        head = null;
    }
}
