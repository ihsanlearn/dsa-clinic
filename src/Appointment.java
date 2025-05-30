public class Appointment {
    int appointmentId;
    int patientId;
    int doctorId;
    String time;

    public Appointment(int appointmentId, int patientId, int doctorId, String time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.time = time;
    }

    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Time: " + time;
    }
}