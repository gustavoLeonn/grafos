package edu.princeton.cs.algs4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GraphColoringDSatur {
    private final Graph graph;

    private int[] colors;          // cor de cada vértice
    private int[] coloringOrder;   // ordem de coloração
    private int colorCount;        // total de cores usadas

    private static final String[] LABELS = {
            "AC", "AL", "AM", "AP", "BA", "CE", "DF",
            "ES", "GO", "MA", "MG", "MS", "MT", "PA",
            "PB", "PE", "PI", "PR", "RJ", "RN", "RO",
            "RR", "RS", "SC", "SE", "SP", "TO"
    };

    public GraphColoringDSatur(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }

        this.graph = graph;

        int V = graph.V();

        colors = new int[V];
        Arrays.fill(colors, -1);

        coloringOrder = new int[V];
        colorCount = 0;
    }

    public Graph getGraph() {
        return graph;
    }

    public void color() {
        int V = graph.V();
        boolean[] colored = new boolean[V];

        Arrays.fill(colors, -1);
        colorCount = 0;

        for (int step = 0; step < V; step++) {

            int selected = -1;
            int bestSaturation = -1;
            int bestDegree = -1;

            for (int v = 0; v < V; v++) {

                if (colored[v]) {
                    continue;
                }

                // calcula saturação (quantidade de cores diferentes nos vizinhos)
                Set<Integer> neighborColors = new HashSet<>();

                int degree = 0;

                for (int w : graph.adj(v)) {
                    degree++;

                    if (colors[w] != -1) {
                        neighborColors.add(colors[w]);
                    }
                }

                int saturation = neighborColors.size();

                // critério DSatur:
                // maior saturação
                // empate -> maior grau
                if (selected == -1
                        || saturation > bestSaturation
                        || (saturation == bestSaturation && degree > bestDegree)) {

                    selected = v;
                    bestSaturation = saturation;
                    bestDegree = degree;
                }
            }

            // encontra a menor cor disponível
            boolean[] used = new boolean[V];

            for (int neighbor : graph.adj(selected)) {
                if (colors[neighbor] != -1) {
                    used[colors[neighbor]] = true;
                }
            }

            int chosenColor = 0;
            while (used[chosenColor]) {
                chosenColor++;
            }

            colors[selected] = chosenColor;
            coloringOrder[step] = selected;
            colored[selected] = true;

            if (chosenColor + 1 > colorCount) {
                colorCount = chosenColor + 1;
            }
        }
    }

    public int getColor(int vertex) {
        validateVertex(vertex);
        return colors[vertex];
    }

    public int getColorCount() {
        return colorCount;
    }

    public int[] getColoringOrder() {
        return Arrays.copyOf(coloringOrder, coloringOrder.length);
    }

    public boolean isValidColoring() {
        for (int v = 0; v < graph.V(); v++) {

            if (colors[v] == -1) {
                return false;
            }

            for (int w : graph.adj(v)) {
                if (colors[v] == colors[w]) {
                    return false;
                }
            }
        }

        return true;
    }

    public String getLabel(int vertex) {
        validateVertex(vertex);

        if (vertex >= LABELS.length) {
            return "V" + vertex;
        }

        return LABELS[vertex];
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= graph.V()) {
            throw new IllegalArgumentException("vertice invalido: " + vertex);
        }
    }
}
