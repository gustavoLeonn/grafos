package edu.princeton.cs.algs4;

public class UnionFind {

    int[] pai;
    int[] rank;

    public UnionFind(int n) {

        pai = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            pai[i] = i;
        }
    }

    public int find(int x) {

        if (pai[x] != x) {
            pai[x] = find(pai[x]);
        }

        return pai[x];
    }

    public boolean union(int a, int b) {

        int ra = find(a);
        int rb = find(b);

        if (ra == rb) {
            return false;
        }

        if (rank[ra] < rank[rb]) {
            pai[ra] = rb;
        }
        else if (rank[ra] > rank[rb]) {
            pai[rb] = ra;
        }
        else {
            pai[rb] = ra;
            rank[ra]++;
        }

        return true;
    }
}
