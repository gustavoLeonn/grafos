package edu.princeton.cs.algs4;

public class Aresta implements Comparable<Aresta> {

    int u;
    int v;
    int peso;

    public Aresta(int u, int v, int peso) {
        this.u = u;
        this.v = v;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta outra) {
        return this.peso - outra.peso;
    }
}