package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Escolha o arquivo aqui
//        In in = new In("dados/tsp10.txt");
          In in = new In("dados/usa13509.txt");

        int width = in.readInt();
        int height = in.readInt();

        List<Point> points = new ArrayList<>();

        while (!in.isEmpty()) {
            points.add(new Point(in.readDouble(), in.readDouble()));
        }

        StdOut.println("Instancia TSP carregada:");
        StdOut.println("- dimensoes: " + width + " x " + height);
        StdOut.println("- numero de pontos: " + points.size());

        Tour nearest = new Tour();
        Tour smallest = new Tour();

        for (Point point : points) {
            nearest.insertNearest(point);
            smallest.insertSmallest(point);
        }

        StdOut.println();
        StdOut.printf("Nearest insertion: tamanho = %d, comprimento = %.4f\n",
                nearest.size(), nearest.length());

        StdOut.printf("Smallest insertion: tamanho = %d, comprimento = %.4f\n",
                smallest.size(), smallest.length());

        TSPVisualizer.showTours(width, height, points, nearest, smallest);
    }
}
