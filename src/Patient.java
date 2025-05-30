public class Patient {
    int id;
    String name;
    int age;
    String address;
    String phone;

    public Patient(int id, String name, int age, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Address: " + address + ", Phone: " + phone;
    }
}