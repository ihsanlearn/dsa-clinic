package structures;

import models.Appointment;

public class AppointmentPriorityQueue {

    class Node {
        Appointment data;
        Node next;

        Node(Appointment data) {
            this.data = data;
        }
    }

    private Node head;

    public void enqueue(Appointment appt) {
        Node newNode = new Node(appt);
        if (head == null || appt.getTimeInMinutes() < head.data.getTimeInMinutes()) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null &&
            current.next.data.getTimeInMinutes() <= appt.getTimeInMinutes()) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public Appointment dequeue() {
        if (head == null) return null;
        Appointment appt = head.data;
        head = head.next;
        return appt;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            Appointment a = temp.data;
            System.out.println("ID: " + a.id + " | Patient ID: " + a.patientId + " | Time: " + a.time);
            temp = temp.next;
        }
    }

    public Appointment[] toArray() {
        int size = 0;
        Node tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }

        Appointment[] arr = new Appointment[size];
        tmp = head;
        int i = 0;
        while (tmp != null) {
            arr[i++] = tmp.data;
            tmp = tmp.next;
        }

        return arr;
    }
}
