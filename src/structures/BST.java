package structures;

import models.Patient;
import java.util.Scanner;

class BSTNode {
    public Patient data;
    public BSTNode left, right;

    public BSTNode(Patient data) {
        this.data = data;
    }
}

public class BST {
    private BSTNode root;

    public void insert(Patient p) {
        root = insertRecursive(root, p);
    }

    private BSTNode insertRecursive(BSTNode current, Patient p) {
        if (current == null) return new BSTNode(p);

        if (p.id < current.data.id) {
            current.left = insertRecursive(current.left, p);
        } else if (p.id > current.data.id) {
            current.right = insertRecursive(current.right, p);
        }
        return current;
    }

    public void searchInput(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Patient found = search(root, id);
        if (found != null) {
            System.out.println("Found: " + found.name + ", " + found.age + " y.o");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private Patient search(BSTNode current, int id) {
        if (current == null) return null;
        if (id == current.data.id) return current.data;
        return id < current.data.id ? search(current.left, id) : search(current.right, id);
    }

    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(BSTNode current) {
        if (current == null) return;
        inOrderRecursive(current.left);
        Patient p = current.data;
        System.out.println(p.id + " - " + p.name);
        inOrderRecursive(current.right);
    }
}
