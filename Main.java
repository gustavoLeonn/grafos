package edu.princeton.cs.algs4;

public class Main {

    private static final int N = 3;

    public static void main(String[] args) {

        Graph grafo = new Graph(N * N);

        int[][] movimentos = {
                {2,1}, {2,-1}, {-2,1}, {-2,-1},
                {1,2}, {1,-2}, {-1,2}, {-1,-2}
        };

        // Construção do grafo
        for (int linha = 0; linha < N; linha++) {
            for (int coluna = 0; coluna < N; coluna++) {

                int v = indice(linha, coluna);

                for (int[] m : movimentos) {
                    int novaLinha = linha + m[0];
                    int novaColuna = coluna + m[1];

                    if (valido(novaLinha, novaColuna)) {
                        int w = indice(novaLinha, novaColuna);

                        if (v < w) {
                            grafo.addEdge(v, w);
                        }
                    }
                }
            }
        }

        // -------------------------------
        // Lista de Adjacência
        // -------------------------------
        System.out.println("Lista de Adjacência:");
        System.out.println(grafo);


        // -------------------------------
        // Componentes Conexas
        // -------------------------------
        CC cc = new CC(grafo);

        System.out.println("Componentes conexas: " + cc.count());

        for (int i = 0; i < cc.count(); i++) {
            System.out.print("Vértices da componente " + i + ": ");

            for (int v = 0; v < grafo.V(); v++) {
                if (cc.id(v) == i) {
                    System.out.print(v + " ");
                }
            }
            System.out.println();
        }


        // -------------------------------
        // Distância mínima (BFS)
        // -------------------------------
        int origem = indice(0,0);
        int destino = indice(2,2);

        BreadthFirstPaths bfs = new BreadthFirstPaths(grafo, origem);

        System.out.println("Distância mínima entre (0,0) e (2,2): "
                + bfs.distTo(destino));


        // -------------------------------
        // Ciclo
        // -------------------------------
        Cycle ciclo = new Cycle(grafo);

        System.out.println("O grafo possui ciclo: "
                + (ciclo.hasCycle() ? "Sim" : "Não"));

        if (ciclo.hasCycle()) {
            System.out.print("Um ciclo encontrado: ");
            for (int v : ciclo.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        }


        // -------------------------------
        // Complexidade
        // -------------------------------
        System.out.println("\nAnálise de Complexidade:");
        System.out.println("Algoritmo utilizado: Busca em Largura (BFS)");
        System.out.println("Complexidade de Tempo: O(V + E)");
        System.out.println("Complexidade de Espaço: O(V)");
    }

    private static boolean valido(int linha, int coluna) {
        return linha >= 0 && linha < N && coluna >= 0 && coluna < N;
    }

    private static int indice(int linha, int coluna) {
        return linha * N + coluna;
    }
}