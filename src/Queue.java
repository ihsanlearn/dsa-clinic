class AppointmentNode {
    Appointment appointment;
    AppointmentNode next;

    public AppointmentNode(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }
}

public class Queue {
    private AppointmentNode front, rear;

    public void scheduleAppointment(Appointment a) {
        AppointmentNode node = new AppointmentNode(a);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
    }

    public void processNextAppointment() {
        if (front != null) {
            System.out.println("Processing: " + front.appointment);
            front = front.next;
            if (front == null) rear = null;
        } else {
            System.out.println("No appointments to process.");
        }
    }

    public void viewUpcomingAppointments() {
        AppointmentNode temp = front;
        while (temp != null) {
            System.out.println(temp.appointment);
            temp = temp.next;
        }
    }
}