class DoctorNode {
    Doctor doctor;
    DoctorNode next;

    public DoctorNode(Doctor doctor) {
        this.doctor = doctor;
        this.next = null;
    }
}

public class DoctorLinkedList {
    private DoctorNode head;

    public void loginDoctor(Doctor doctor) {
        DoctorNode newNode = new DoctorNode(doctor);
        newNode.next = head;
        head = newNode;
    }

    public void logoutDoctor(int doctorId) {
        if (head == null) return;
        if (head.doctor.id == doctorId) {
            head = head.next;
            return;
        }
        DoctorNode current = head;
        while (current.next != null && current.next.doctor.id != doctorId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void getAllLoggedInDoctors() {
        DoctorNode current = head;
        while (current != null) {
            System.out.println(current.doctor);
            current = current.next;
        }
    }

    public void getLastLoggedInDoctor() {
        if (head != null) {
            System.out.println(head.doctor);
        } else {
            System.out.println("no doctor is currently logged in!!");
        }
    }
}