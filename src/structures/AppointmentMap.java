package structures;

public class AppointmentMap {
    private int[] doctorIds = new int[100];
    private AppointmentPriorityQueue[] queues = new AppointmentPriorityQueue[100];
    private int size = 0;

    public AppointmentPriorityQueue getQueue(int doctorId) {
        for (int i = 0; i < size; i++) {
            if (doctorIds[i] == doctorId) {
                return queues[i];
            }
        }
        doctorIds[size] = doctorId;
        queues[size] = new AppointmentPriorityQueue();
        size++;
        return queues[size - 1];
    }

    public int[] getDoctorIds() {
        int[] ids = new int[size];
        for (int i = 0; i < size; i++) ids[i] = doctorIds[i];
        return ids;
    }
}
