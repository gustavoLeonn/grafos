package edu.princeton.cs.algs4;

public class Main {

    public static void main(String[] args) {

        In in = new In("dados/entrada_eulerizada.txt");

        Digraph digraph = new Digraph(in);

        System.out.println("=== Grau dos vértices ===");

        boolean balanceado = true;

        for (int v = 0; v < digraph.V(); v++) {

            int inDegree = digraph.indegree(v);
            int outDegree = digraph.outdegree(v);

            System.out.println(
                    v + " -> Entrada: " + inDegree +
                            " | Saída: " + outDegree
            );

            if (inDegree != outDegree)
                balanceado = false;
        }

        System.out.println();

        if (balanceado)
            System.out.println("Grafo Balanceado");
        else
            System.out.println("Grafo NÃO Balanceado");

        System.out.println();

        DirectedEulerianCycle euler =
                new DirectedEulerianCycle(digraph);

        if (!euler.hasEulerianCycle()) {
            System.out.println("Não existe circuito Euleriano");
            return;
        }

        System.out.println("Circuito Euleriano:");

        double custoTotal = 0;

        int anterior = -1;

        for (int v : euler.cycle()) {

            System.out.print(v + " ");

            if (anterior != -1)
                custoTotal += digraph.getPeso(anterior, v);

            anterior = v;
        }

        System.out.println();
        System.out.println();
        System.out.println("Custo Total = " + custoTotal);
    }
}
