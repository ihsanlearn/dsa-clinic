package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGenerator {
    private static int patientId = 1;
    private static int doctorId = 1;
    private static int appointmentId = 1;

    public static int getNextPatientId() {
        return patientId++;
    }

    public static int getNextDoctorId() {
        return doctorId++;
    }

    public static int getNextAppointmentId() {
        return appointmentId++;
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    public static void updateMaxIds(int pid, int did, int aid) {
        if (pid >= patientId) patientId = pid + 1;
        if (did >= doctorId) doctorId = did + 1;
        if (aid >= appointmentId) appointmentId = aid + 1;
    }
}
