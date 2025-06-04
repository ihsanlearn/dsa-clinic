package structures;

import models.Patient;

public class PatientBST {

    class Node {
        Patient data;
        Node left, right;

        Node(Patient data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(Patient p) {
        root = insertRecursive(root, p);
    }

    private Node insertRecursive(Node root, Patient p) {
        if (root == null) return new Node(p);
        if (p.id < root.data.id)
            root.left = insertRecursive(root.left, p);
        else
            root.right = insertRecursive(root.right, p);
        return root;
    }

    public Patient search(int id) {
        Node current = root;
        while (current != null) {
            if (id == current.data.id) return current.data;
            current = (id < current.data.id) ? current.left : current.right;
        }
        return null;
    }

    public void inOrderDisplay() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            Patient p = node.data;
            System.out.println(p.id + " | " + p.name + " | " + p.age + " | " + p.address + " | " + p.phone);
            inOrderRecursive(node.right);
        }
    }

    public void reset() {
        root = null;
    }
}
