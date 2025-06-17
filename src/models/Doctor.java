package models;

public class Doctor {
    public int id;
    public String name;
    public String specialty;
    public String password;
    public String status;

    public Doctor(int id, String name, String specialty, String password, String status) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.password = password;
        this.status = status;
    }

    public String serialize() {
        return id + "|" + name + "|" + specialty + "|" + password + "|" + status;
    }

    public static Doctor deserialize(String line) {
        String[] parts = line.split("\\|");
        return new Doctor(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            parts[3],
            parts[4]
        );
    }
}
