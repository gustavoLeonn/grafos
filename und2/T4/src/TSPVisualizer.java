package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.List;

public class TSPVisualizer {
    private static final int BORDER = 70;

    public static void showTours(int width, int height, List<Point> points, Tour nearest, Tour smallest) {
        StdDraw.setCanvasSize(width, height + BORDER);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(-BORDER, height);
        StdDraw.enableDoubleBuffering();
        render(points, nearest, smallest);
    }

    private static void render(List<Point> points, Tour nearest, Tour smallest) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.004);
        nearest.draw();

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.003);
        smallest.draw();

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (Point p : points) {
            p.draw();
        }

        StdDraw.textLeft(10, -10, "num points: " + points.size());
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.textLeft(10, -35, String.format("nearest: %.4f", nearest.length()));
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.textLeft(10, -60, String.format("smallest: %.4f", smallest.length()));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show();
    }
}
