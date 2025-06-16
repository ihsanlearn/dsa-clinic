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

    // public String serialize() {
    //     return id + "\t" + name + "\t\t" + age + "\t" + address + "\t\t" + phone;
    // }

    // public static Patient deserialize(String line) {
    //     String[] parts = line.split("\t");
    //     return new Patient(
    //         Integer.parseInt(parts[0]),
    //         parts[1],
    //         Integer.parseInt(parts[2]),
    //         parts[3],
    //         parts[4]
    //     );
    // }

    public String serialize() {
        return String.format("%-4d%-30s%-5d%-25s%s", id, name, age, address, phone);
    }

    public static Patient deserialize(String line) {
        int id = Integer.parseInt(line.substring(0, 4).trim());
        String name = line.substring(4, 34).trim();
        int age = Integer.parseInt(line.substring(34, 39).trim());
        String address = line.substring(39, 64).trim();
        String phone = line.substring(64).trim();

        return new Patient(id, name, age, address, phone);
    }

}
