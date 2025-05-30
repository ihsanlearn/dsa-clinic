public class Doctor {
    int id;
    String name;
    String specialty;
    String loginTime;

    public Doctor(int id, String name, String specialty, String loginTime) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.loginTime = loginTime;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Specialty: " + specialty + ", Login Time: " + loginTime;
    }
}