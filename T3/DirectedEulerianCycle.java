package edu.princeton.cs.algs4;

import java.util.Iterator;

public class DirectedEulerianCycle {
    private Stack<Integer> cycle = null;  // Eulerian cycle; null if no such cycle


    public DirectedEulerianCycle(Digraph digraph) {

        if (digraph.E() == 0) return;

        for (int v = 0; v < digraph.V(); v++)
            if (digraph.outdegree(v) != digraph.indegree(v))
                return;

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[digraph.V()];
        for (int v = 0; v < digraph.V(); v++)
            adj[v] = digraph.adj(v).iterator();

        int s = nonIsolatedVertex(digraph);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }
            cycle.push(v);
        }

        if (cycle.size() != digraph.E() + 1)
            cycle = null;

        assert certifySolution(digraph);
    }


    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    private static int nonIsolatedVertex(Digraph digraph) {
        for (int v = 0; v < digraph.V(); v++)
            if (digraph.outdegree(v) > 0)
                return v;
        return -1;
    }


    private static boolean satisfiesNecessaryAndSufficientConditions(Digraph digraph) {

        if (digraph.E() == 0) return false;

        for (int v = 0; v < digraph.V(); v++)
            if (digraph.outdegree(v) != digraph.indegree(v))
                return false;

        Graph H = new Graph(digraph.V());
        for (int v = 0; v < digraph.V(); v++)
            for (int w : digraph.adj(v))
                H.addEdge(v, w);

        int s = nonIsolatedVertex(digraph);
        BreadthFirstPaths bfs = new BreadthFirstPaths(H, s);
        for (int v = 0; v < digraph.V(); v++)
            if (H.degree(v) > 0 && !bfs.hasPathTo(v))
                return false;

        return true;
    }

    private boolean certifySolution(Digraph digraph) {

        if (hasEulerianCycle() == (cycle() == null)) return false;

        if (hasEulerianCycle() != satisfiesNecessaryAndSufficientConditions(digraph)) return false;

        if (cycle == null) return true;

        if (cycle.size() != digraph.E() + 1) return false;

        return true;
    }


    private static void unitTest(Digraph digraph, String description) {
        StdOut.println(description);
        StdOut.println("-------------------------------------");
        StdOut.print(digraph);

        DirectedEulerianCycle euler = new DirectedEulerianCycle(digraph);

        StdOut.print("Eulerian cycle: ");
        if (euler.hasEulerianCycle()) {
            for (int v : euler.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("none");
        }
        StdOut.println();
    }
}
