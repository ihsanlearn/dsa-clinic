package structures;

import models.*;
import utils.*;
import java.util.Scanner;

public class AppointmentMap {
    private final int MAX_DOCTOR = 100;
    private int[] doctorIds = new int[MAX_DOCTOR];
    private PriorityQueue[] queues = new PriorityQueue[MAX_DOCTOR];
    private int count = 0;

    public void schedule(Scanner sc, LinkedList patients, LinkedList doctors) {
        System.out.print("Patient ID: ");
        int pid = Integer.parseInt(sc.nextLine());

        System.out.print("Doctor ID: ");
        int did = Integer.parseInt(sc.nextLine());

        Doctor d = findDoctorById(doctors, did);
        if (d == null || d.status.equals("inactive")) {
            System.out.println("Doctor not found or not active.");
            return;
        }

        System.out.print("Time (HH:mm): ");
        String time = sc.nextLine();

        Appointment a = new Appointment(IdGenerator.getNextAppointmentId(), pid, did, time);
        getQueue(did).enqueue(a);
        System.out.println("Appointment scheduled.");
    }

    public void process(Scanner sc, LinkedList doctors) {
        System.out.print("Doctor ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Doctor d = findDoctorById(doctors, id);
        if (d == null || d.status.equals("inactive")) {
            System.out.println("Doctor not logged in.");
            return;
        }

        Appointment a = getQueue(id).dequeue();
        if (a == null) {
            System.out.println("No appointments.");
        } else {
            System.out.println("Processing appointment: " + a.id + " at " + a.time);
        }
    }

    public void display(Scanner sc) {
        System.out.print("Doctor ID: ");
        int id = Integer.parseInt(sc.nextLine());
        getQueue(id).display();
    }

    private Doctor findDoctorById(LinkedList doctors, int id) {
        Node<Object> temp = doctors.getHead();
        while (temp != null) {
            if (temp.data instanceof Doctor d && d.id == id) return d;
            temp = temp.next;
        }
        return null;
    }

    public PriorityQueue getQueue(int doctorId) {
        for (int i = 0; i < count; i++) {
            if (doctorIds[i] == doctorId) return queues[i];
        }
        doctorIds[count] = doctorId;
        queues[count] = new PriorityQueue();
        count++;
        return queues[count - 1];
    }

    public int getCount() {
        return count;
    }

    public int getDoctorId(int i) {
        return doctorIds[i];
    }

    public PriorityQueue getQueueByIndex(int i) {
        return queues[i];
    }
}
