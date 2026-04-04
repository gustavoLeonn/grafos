package edu.princeton.cs.algs4;

public class Main {

    public static void main(String[] args) {

        In in = new In("dados/entrada_eulerizada.txt");

        Digraph digraph = new Digraph(in);

        System.out.println("=== Grau dos Vértices ===");

        boolean balanceado = true;

        for (int v = 0; v < digraph.V(); v++) {

            int entrada = digraph.indegree(v);
            int saida = digraph.outdegree(v);

            System.out.println(
                    v +
                            " -> Entrada: " + entrada +
                            " | Saída: " + saida
            );

            if (entrada != saida)
                balanceado = false;
        }

        System.out.println();

        if (balanceado)
            System.out.println("Grafo está balanceado");
        else
            System.out.println("Grafo NÃO está balanceado");


        System.out.println();
        System.out.println("=== Método de Hierholzer ===");

        DirectedEulerianCycle euler =
                new DirectedEulerianCycle(digraph);

        if (!euler.hasEulerianCycle()) {
            System.out.println("Não existe circuito Euleriano");
            return;
        }

        System.out.println("Circuito Euleriano:");

        for (int v : euler.cycle())
            System.out.print(v + " ");

        System.out.println();
    }
}
