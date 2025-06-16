package structures;

import models.Appointment;

class AppNode {
    public Appointment data;
    public AppNode next;

    public AppNode(Appointment data) {
        this.data = data;
    }
}

public class PriorityQueue {
    private AppNode head;

    public void enqueue(Appointment a) {
        AppNode newNode = new AppNode(a);
        if (head == null || a.getPriority() < head.data.getPriority()) {
            newNode.next = head;
            head = newNode;
        } else {
            AppNode current = head;
            while (current.next != null && current.next.data.getPriority() <= a.getPriority()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public Appointment dequeue() {
        if (head == null) return null;
        Appointment a = head.data;
        head = head.next;
        return a;
    }

    public void display() {
        AppNode temp = head;
        while (temp != null) {
            Appointment a = temp.data;
            System.out.println("Appointment ID: " + a.id + ", Time: " + a.time);
            temp = temp.next;
        }
    }

    public AppNode getHead() {
        return head;
    }

    public Appointment[] getAllAppointments() {
    int size = 0;
    AppNode temp = head;
    while (temp != null) {
        size++;
        temp = temp.next;
    }

    Appointment[] result = new Appointment[size];
    temp = head;
    int i = 0;
    while (temp != null) {
        result[i++] = temp.data;
        temp = temp.next;
    }
    return result;
}

}
