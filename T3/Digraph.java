package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;
import java.util.HashMap;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;

    // NOVO — armazenar pesos
    private HashMap<String, Queue<Double>> pesos;

    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;

        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];

        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();

        pesos = new HashMap<>();
    }

    // CONSTRUTOR ALTERADO
    public Digraph(In in) {

        if (in == null) throw new IllegalArgumentException("argument is null");

        try {

            this.V = in.readInt();
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];

            for (int v = 0; v < V; v++)
                adj[v] = new Bag<Integer>();

            pesos = new HashMap<>();

            int E = in.readInt();

            for (int i = 0; i < E; i++) {

                int v = in.readInt();
                int w = in.readInt();
                double peso = in.readDouble();   // LENDO PESO

                addEdge(v, w);

                String key = v + "-" + w;

                if (!pesos.containsKey(key))
                    pesos.put(key, new Queue<Double>());

                pesos.get(key).enqueue(peso);
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format", e);
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException(
                    "vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    // NOVO — pegar peso da aresta
    public double getPeso(int v, int w) {

        String key = v + "-" + w;

        if (!pesos.containsKey(key))
            return 0;

        return pesos.get(key).dequeue();
    }

    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append(V + " vertices, " + E + " edges " + NEWLINE);

        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v])
                s.append(w + " ");
            s.append(NEWLINE);
        }

        return s.toString();
    }
}
