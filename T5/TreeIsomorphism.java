package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeIsomorphism {
    private final Graph graph;
    private Boolean isTreeMemo = null; // Para cachear o resultado da validação

    public TreeIsomorphism(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }


    public boolean isTree() {
        if (isTreeMemo != null) {
            return isTreeMemo;
        }

        int V = graph.V();
        int E = graph.E();

        if (V == 0) {
            isTreeMemo = true;
            return true;
        }

        // Uma árvore deve ter exatamente V - 1 arestas
        if (E != V - 1) {
            isTreeMemo = false;
            return false;
        }

        // Verifica se o grafo é conexo usando DFS
        boolean[] visited = new boolean[V];
        dfs(0, visited);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                isTreeMemo = false;
                return false; // Grafo desconexo
            }
        }

        isTreeMemo = true;
        return true;
    }

    // Busca em profundidade auxiliar para checar conectividade
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, visited);
            }
        }
    }

    public String getValidationMessage() {
        if (isTree()) {
            return "A entrada é uma árvore válida (conexa e E = V - 1).";
        } else {
            return "A entrada NÃO é uma árvore válida (pode ser desconexa ou ter ciclos).";
        }
    }

    /**
     * Encontra os centros da árvore removendo folhas iterativamente.
     */
    public int[] getCenters() {
        if (!isTree()) {
            throw new IllegalStateException("Não é possível encontrar o centro de um grafo que não é árvore.");
        }

        int V = graph.V();
        if (V <= 2) {
            int[] centers = new int[V];
            for (int i = 0; i < V; i++) centers[i] = i;
            return centers;
        }

        int[] degree = new int[V];
        List<Integer> L = new ArrayList<>();

        // calcular o grau de cada vértice e colocar em L todos os vértices com grau 0 ou 1
        for (int v = 0; v < V; v++) {
            degree[v] = graph.degree(v);
            if (degree[v] <= 1) {
                L.add(v);
            }
        }

        int processados = L.size();

        // enquanto processados < |V| faça
        while (processados < V) {
            List<Integer> novasFolhas = new ArrayList<>();
            // para cada vértice u em L
            for (int u : L) {
                // para cada vizinho v de u
                for (int v : graph.adj(u)) {
                    degree[v]--; // decrementar o grau de v
                    // se o grau de v se tornar 1, inserir v em novasFolhas
                    if (degree[v] == 1) {
                        novasFolhas.add(v);
                    }
                }
            }
            processados += novasFolhas.size();
            L = novasFolhas; // L <- novasFolhas
        }

        // devolver L (que contem 1 ou 2 centros)
        int[] result = new int[L.size()];
        for (int i = 0; i < L.size(); i++) {
            result[i] = L.get(i);
        }
        return result;
    }

    /**
     * Gera a codificação canônica da árvore.
     */
    public String getCanonicalEncoding() {
        if (!isTree()) {
            return "[Erro: Não é árvore]";
        }

        int[] centers = getCenters();

        // Se há apenas um centro, enraiza e codifica
        if (centers.length == 1) {
            return encodeRecursive(centers[0], -1);
        }
        // Se houver dois centros, devemos testar enraizar a partir dos dois
        // Para ser sempre único, selecionamos lexicograficamente a maior (ou menor) representação.
        else if (centers.length == 2) {
            String enc1 = encodeRecursive(centers[0], -1);
            String enc2 = encodeRecursive(centers[1], -1);

            // Retorna o menor código lexicográfico como padrão canônico consistente
            if (enc1.compareTo(enc2) < 0) {
                return enc1;
            } else {
                return enc2;
            }
        }

        return "";
    }

    /**
     * Função recursiva que enraíza e gera o código das subárvores.
     */
    private String encodeRecursive(int u, int parent) {
        List<String> childrenCodes = new ArrayList<>();

        // para cada filho 'c' de 'u' (vizinhança exceto o pai)
        for (int v : graph.adj(u)) {
            if (v != parent) {
                childrenCodes.add(encodeRecursive(v, u));
            }
        }

        // se u não possui filhos (é folha), sua lista childrenCodes está vazia.
        if (childrenCodes.isEmpty()) {
            return "()";
        }

        // ordenar lexicograficamente os códigos dos filhos
        Collections.sort(childrenCodes);

        // concatenar os códigos ordenados e envolvê-los com parênteses
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (String code : childrenCodes) {
            sb.append(code);
        }
        sb.append(")");

        return sb.toString();
    }
}