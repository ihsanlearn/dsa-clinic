package utils;

import models.*;
import structures.*;

import java.io.*;

public class FileManager {

    public static void loadAll(LinkedList patientList, LinkedList doctorList, BST patientTree, AppointmentMap appointmentMap) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/patients.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                Patient p = Patient.deserialize(line);
                patientList.insert(p);
                patientTree.insert(p);
                IdGenerator.updateMaxIds(p.id, 0, 0);
            }
            br.close();

            br = new BufferedReader(new FileReader("data/doctors.txt"));
            while ((line = br.readLine()) != null) {
                Doctor d = Doctor.deserialize(line);
                doctorList.insert(d);
                IdGenerator.updateMaxIds(0, d.id, 0);
            }
            br.close();

            br = new BufferedReader(new FileReader("data/appointments.txt"));
            while ((line = br.readLine()) != null) {
                Appointment a = Appointment.deserialize(line);
                appointmentMap.getQueue(a.doctorId).enqueue(a);
                IdGenerator.updateMaxIds(0, 0, a.id);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("(error loading data) " + e.getMessage());
        }
    }

    public static void saveAll(LinkedList patientList, LinkedList doctorList, BST patientTree, AppointmentMap appointmentMap) {
        try {
            PrintWriter pw = new PrintWriter("data/patients.txt");
            Node<Object> temp = patientList.getHead();
            while (temp != null) {
                if (temp.data instanceof Patient p) pw.println(p.serialize());
                temp = temp.next;
            }
            pw.close();

            pw = new PrintWriter("data/doctors.txt");
            temp = doctorList.getHead();
            while (temp != null) {
                if (temp.data instanceof Doctor d) pw.println(d.serialize());
                temp = temp.next;
            }
            pw.close();

            pw = new PrintWriter("data/appointments.txt");
            for (int i = 0; i < appointmentMap.getCount(); i++) {
                PriorityQueue q = appointmentMap.getQueueByIndex(i);
                for (Appointment a : q.getAllAppointments()) {
                    pw.println(a.serialize());
                }

            }
            pw.close();
        } catch (IOException e) {
            System.out.println("(error saving data) " + e.getMessage());
        }
    }
}
