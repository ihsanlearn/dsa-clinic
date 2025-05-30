class PatientNode {
    Patient patient;
    PatientNode next;

    public PatientNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}

public class LinkedList {
    private PatientNode head;

    public void addPatient(Patient patient) {
        PatientNode newNode = new PatientNode(patient);
        if (head == null) {
            head = newNode;
        } else {
            PatientNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void removePatientById(int id) {
        if (head == null) return;
        if (head.patient.id == id) {
            head = head.next;
            return;
        }
        PatientNode current = head;
        while (current.next != null && current.next.patient.id != id) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public Patient findPatientByName(String name) {
        PatientNode current = head;
        while (current != null) {
            if (current.patient.name.equalsIgnoreCase(name)) {
                return current.patient;
            }
            current = current.next;
        }
        return null;
    }

    public void displayAllPatients() {
        PatientNode current = head;
        while (current != null) {
            System.out.println(current.patient);
            current = current.next;
        }
    }
}