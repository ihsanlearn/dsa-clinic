package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import models.*;
import structures.*;

public class FileManager {

    public static void savePatients(PatientLinkedList list, String filepath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            Patient[] data = list.toArray();
            for (int i = 0; i < data.length; i++) {
                writer.println(data[i].serialize());
            }
        } catch (IOException ignored) {}
    }

    public static void loadPatients(PatientLinkedList list, String filepath) {
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.addPatient(Patient.deserialize(line));
            }
        } catch (IOException ignored) {}
    }

    public static void saveDoctors(String filepath, Doctor[] doctors, int size) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            for (int i = 0; i < size; i++) {
                writer.println(doctors[i].serialize());
            }
        } catch (IOException ignored) {}
    }

    public static int loadDoctors(String filepath, Doctor[] doctors) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null && count < doctors.length) {
                doctors[count++] = Doctor.deserialize(line);
            }
        } catch (IOException ignored) {}
        return count;
    }

    public static void saveAppointments(AppointmentMap map, String filepath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            int[] ids = map.getDoctorIds();
            for (int i = 0; i < ids.length; i++) {
                AppointmentPriorityQueue queue = map.getQueue(ids[i]);
                Appointment[] list = queue.toArray();
                for (int j = 0; j < list.length; j++) {
                    writer.println(list[j].serialize());
                }
            }
        } catch (IOException ignored) {}
    }

    public static void loadAppointments(AppointmentMap map, String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Appointment a = Appointment.deserialize(line);
                map.getQueue(a.doctorId).enqueue(a);
            }
        } catch (IOException ignored) {}
    }

    public static void updateLogin(Doctor doctor, String filepath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
                for (String line : lines) {
                    // Misalnya cocokkan berdasarkan ID
                    if (line.startsWith(doctor.id + ";")) {
                        writer.println(doctor.serialize());
                    } else {
                        writer.println(line); // baris lain tetap ditulis ulang seperti sebelumnya
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLogout(Doctor doctor, String filepath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
                for (String line : lines) {
                    // Misalnya cocokkan berdasarkan ID
                    if (line.startsWith(doctor.id + ";")) {
                        writer.println(doctor.serialize());
                    } else {
                        writer.println(line); // baris lain tetap ditulis ulang seperti sebelumnya
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSession(DoctorLoginList doctors, String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Doctor doctor = Doctor.deserialize(line);
                if (!doctor.loginTime.equals("inactive")) {
                    doctors.loginDoctor(doctor);
                }
            }
        } catch (IOException ignored) {}
    }

    public static void clearSession(String filepath) {
        new File(filepath).delete();
    }

    public static int generateNewId(String filepath) {
        int newId = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            String lastLine = null;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lastLine = line;
                }
            }

            if (lastLine != null) {
                String[] parts = lastLine.split(";");
                if (parts.length > 0) {
                    newId = Integer.parseInt(parts[0]) + 1;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return newId;
    }

}
