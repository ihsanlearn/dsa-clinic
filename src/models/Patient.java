package models;

public class Patient {
    public int id;
    public String name;
    public int age;
    public String address;
    public String phone;

    public Patient(int id, String name, int age, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public String serialize() {
        return id + ";" + name + ";" + age + ";" + address + ";" + phone;
    }

    public static Patient deserialize(String line) {
        String[] parts = line.split(";");
        return new Patient(
            Integer.parseInt(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            parts[3],
            parts[4]
        );
    }
}
