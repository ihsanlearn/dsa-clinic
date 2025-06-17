package models;

public class Appointment {
    public int id;
    public int patientId;
    public int doctorId;
    public String time;

    public Appointment(int id, int patientId, int doctorId, String time) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.time = time;
    }

    public String serialize() {
        return id + "|" + patientId + "|" + doctorId + "|" + time;
    }

    public static Appointment deserialize(String line) {
        String[] parts = line.split("\\|");
        return new Appointment(
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            parts[3]
        );
    }

    public int getPriority() {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
