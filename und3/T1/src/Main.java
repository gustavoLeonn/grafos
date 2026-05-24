package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        In entrada = new In("dados/tree1.txt");

        int n = entrada.readInt();

        Ponto[] pontos = new Ponto[n];

        for (int i = 0; i < n; i++) {

            int x = entrada.readInt();
            int y = entrada.readInt();

            pontos[i] = new Ponto(x, y);
        }

        List<Aresta> arestas = new ArrayList<>();

        // cria todas as arestas possíveis
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int dist =
                        Math.abs(pontos[i].x - pontos[j].x) +
                                Math.abs(pontos[i].y - pontos[j].y);

                arestas.add(new Aresta(i, j, dist));
            }
        }

        // ordena por peso
        Collections.sort(arestas);

        UnionFind uf = new UnionFind(n);

        int total = 0;

        System.out.println("ARESTAS DA MST:\n");

        for (Aresta a : arestas) {

            // evita ciclos
            if (uf.union(a.u, a.v)) {

                total += a.peso;

                System.out.println(
                        a.u + " -> " +
                                a.v + " = " +
                                a.peso
                );
            }
        }

        System.out.println("\nTOTAL = " + total);
    }
}
