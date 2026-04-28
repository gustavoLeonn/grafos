package edu.princeton.cs.algs4;

public class Main {

    // posições fixas para desenhar os 27 estados
    private static final double[][] POS = {
            {0.10, 0.60}, // AC
            {0.55, 0.55}, // AL
            {0.18, 0.75}, // AM
            {0.38, 0.92}, // AP
            {0.48, 0.60}, // BA
            {0.58, 0.72}, // CE
            {0.42, 0.52}, // DF
            {0.52, 0.42}, // ES
            {0.38, 0.55}, // GO
            {0.48, 0.78}, // MA
            {0.42, 0.42}, // MG
            {0.28, 0.42}, // MS
            {0.28, 0.58}, // MT
            {0.35, 0.82}, // PA
            {0.62, 0.62}, // PB
            {0.60, 0.58}, // PE
            {0.52, 0.68}, // PI
            {0.30, 0.25}, // PR
            {0.48, 0.35}, // RJ
            {0.65, 0.68}, // RN
            {0.15, 0.50}, // RO
            {0.22, 0.92}, // RR
            {0.28, 0.10}, // RS
            {0.30, 0.18}, // SC
            {0.56, 0.52}, // SE
            {0.35, 0.32}, // SP
            {0.42, 0.72}  // TO
    };

    public static void main(String[] args) {

        In in = new In("dados/brasil.txt");
        Graph graph = new Graph(in);

        GraphColoringDSatur dsatur = new GraphColoringDSatur(graph);
        dsatur.color();

        // ======================================
        // LISTA DE ADJACÊNCIA
        // ======================================
        System.out.println("======================================");
        System.out.println("LISTA DE ADJACENCIA DO GRAFO");
        System.out.println("======================================");

        for (int v = 0; v < graph.V(); v++) {
            System.out.print(
                    v + " (" + dsatur.getLabel(v) + ") -> "
            );

            for (int w : graph.adj(v)) {
                System.out.print(
                        w + " (" + dsatur.getLabel(w) + ") "
                );
            }

            System.out.println();
        }


        System.out.println();
        System.out.println("ORDEM DE COLORACAO");

        int[] ordem = dsatur.getColoringOrder();

        for (int i = 0; i < ordem.length; i++) {
            int v = ordem[i];

            System.out.println(
                    (i + 1) + "º -> Vertice " +
                            v + " (" + dsatur.getLabel(v) + ")"
            );
        }

        System.out.println();
        System.out.println("COR ATRIBUIDA A CADA ESTADO");

        for (int v = 0; v < graph.V(); v++) {

            int cor = dsatur.getColor(v);
            String nomeCor;

            switch (cor) {
                case 0:
                    nomeCor = "Vermelho";
                    break;
                case 1:
                    nomeCor = "Azul";
                    break;
                case 2:
                    nomeCor = "Verde";
                    break;
                case 3:
                    nomeCor = "Amarelo";
                    break;
                case 4:
                    nomeCor = "Laranja";
                    break;
                default:
                    nomeCor = "Cinza";
            }

            System.out.println(
                    "Vertice " + v +
                            " (" + dsatur.getLabel(v) + ")" +
                            " -> " + nomeCor
            );
        }

        System.out.println();
        System.out.println("TOTAL DE CORES UTILIZADAS");
        System.out.println(dsatur.getColorCount());

        System.out.println();
        System.out.println("VALIDACAO DA COLORACAO");
        System.out.println(
                dsatur.isValidColoring()
                        ? "Coloracao valida."
                        : "Coloracao invalida."
        );


        desenharGrafo(graph, dsatur);
    }

    private static void desenharGrafo(Graph graph, GraphColoringDSatur dsatur) {

        StdDraw.setCanvasSize(1000, 800);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.clear();

        // desenha arestas
        StdDraw.setPenRadius(0.002);

        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (v < w) {
                    StdDraw.line(
                            POS[v][0], POS[v][1],
                            POS[w][0], POS[w][1]
                    );
                }
            }
        }

        // desenha vértices coloridos
        for (int v = 0; v < graph.V(); v++) {

            int cor = dsatur.getColor(v);

            switch (cor) {
                case 0:
                    StdDraw.setPenColor(StdDraw.RED);
                    break;
                case 1:
                    StdDraw.setPenColor(StdDraw.BLUE);
                    break;
                case 2:
                    StdDraw.setPenColor(StdDraw.GREEN);
                    break;
                case 3:
                    StdDraw.setPenColor(StdDraw.YELLOW);
                    break;
                case 4:
                    StdDraw.setPenColor(StdDraw.ORANGE);
                    break;
                default:
                    StdDraw.setPenColor(StdDraw.GRAY);
            }

            StdDraw.filledCircle(
                    POS[v][0],
                    POS[v][1],
                    0.025
            );

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(
                    POS[v][0],
                    POS[v][1],
                    dsatur.getLabel(v)
            );
        }
    }
}