class BSTNode {
    Patient patient;
    BSTNode left, right;

    public BSTNode(Patient patient) {
        this.patient = patient;
    }
}

public class BST {
    private BSTNode root;

    public void insertPatient(Patient p) {
        root = insert(root, p);
    }

    private BSTNode insert(BSTNode node, Patient p) {
        if (node == null) return new BSTNode(p);
        if (p.id < node.patient.id) node.left = insert(node.left, p);
        else if (p.id > node.patient.id) node.right = insert(node.right, p);
        return node;
    }

    public Patient searchPatient(int id) {
        BSTNode current = root;
        while (current != null) {
            if (id == current.patient.id) return current.patient;
            else if (id < current.patient.id) current = current.left;
            else current = current.right;
        }
        return null;
    }

    public void inOrderDisplay() {
        inOrder(root);
    }

    private void inOrder(BSTNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.patient);
            inOrder(node.right);
        }
    }
}