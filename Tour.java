package edu.princeton.cs.algs4;

public class Tour {
    private class Node {
        private Point p; // point value of node
        private Node next; // pointer to next Node
    }

    private Node start; // first Node in Linked List

    // creates an empty tour
    public Tour() {
        start = new Node();
    }

    // creates the 4-point tour a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d) {
        start = new Node();
        Node b1 = new Node();
        Node c1 = new Node();
        Node d1 = new Node();
        start.p = a;
        b1.p = b;
        c1.p = c;
        d1.p = d;
        start.next = b1;
        b1.next = c1;
        c1.next = d1;
        d1.next = start;
    }

    // returns the number of points in this tour
    public int size() {
        if (start.p == null) {
            return 0;
        }
        else {
            int counter = 0;
            Node current = start;
            do {
                current = current.next;
                counter += 1;
            } while (!current.equals(start));
            return counter;
        }
    }

    // returns the length of this tour
    public double length() {
        if (start.p == null) {
            return 0.0;
        }
        else {
            double distance = 0.0;
            Node current = start;
            do {
                distance += current.p.distanceTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));

            return distance;
        }

    }

    // returns a string representation of this tour
    public String toString() {
        if (start.p == null) {
            return "";
        }
        else {
            Node current = start;
            StringBuilder str = new StringBuilder();
            do {
                str.append(current.p.toString() + "\n");
                current = current.next;
            } while (!current.equals(start));
            return str.toString();
        }
    }

    // draws this tour to standard drawing
    public void draw() {
        if (start.p != null && start.next != null) {
            Node current = start;
            do {
                current.p.drawTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));
        }
    }

    // inserts p using the nearest neighbor heuristic
    public void insertNearest(Point p) {

        if (start.p == null) {
            start.p = p;
            start.next = start;
            return;
        }

        Node nearest = start;
        Node current = start;

        double min = p.distanceTo(start.p);

        current = current.next;

        while (!current.equals(start)) {

            double dist = p.distanceTo(current.p);

            if (dist < min) {
                min = dist;
                nearest = current;
            }

            current = current.next;
        }

        Node newNode = new Node();
        newNode.p = p;

        newNode.next = nearest.next;
        nearest.next = newNode;
    }


    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point p) {

        // lista vazia
        if (start.p == null) {
            start.p = p;
            start.next = start;
            return;
        }

        // apenas um ponto
        if (start.next == start) {
            Node newNode = new Node();
            newNode.p = p;
            newNode.next = start;
            start.next = newNode;
            return;
        }

        Node best = start;
        Node current = start;

        double minIncrease = Double.POSITIVE_INFINITY;

        do {

            double increase =
                    current.p.distanceTo(p)
                            + p.distanceTo(current.next.p)
                            - current.p.distanceTo(current.next.p);

            if (increase < minIncrease) {
                minIncrease = increase;
                best = current;
            }

            current = current.next;

        } while (!current.equals(start));

        Node newNode = new Node();
        newNode.p = p;

        newNode.next = best.next;
        best.next = newNode;
    }

}