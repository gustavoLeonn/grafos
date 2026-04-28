package edu.princeton.cs.algs4;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String caminhoArquivo1 = "dados/tree1.txt";
        String caminhoArquivo2 = "dados/tree2.txt";

        try {
            // Leitura dos arquivos e construção dos grafos usando a classe base
            Graph tree1 = new Graph(new In(caminhoArquivo1));
            Graph tree2 = new Graph(new In(caminhoArquivo2));

            StdOut.println("Árvore 1 (Lista de Adjacência):");
            StdOut.println(tree1);

            StdOut.println("Árvore 2 (Lista de Adjacência):");
            StdOut.println(tree2);

            TreeIsomorphism analysis1 = new TreeIsomorphism(tree1);
            TreeIsomorphism analysis2 = new TreeIsomorphism(tree2);

            StdOut.println("Validação de Entrada");
            StdOut.println("Árvore 1: " + analysis1.getValidationMessage());
            StdOut.println("Árvore 2: " + analysis2.getValidationMessage());

            // Se alguma não for árvore, interrompe a comparação
            if (!analysis1.isTree() || !analysis2.isTree()) {
                StdOut.println("\n[AVISO] Uma ou ambas as entradas não são árvores válidas. Interrompendo comparação.");
                return;
            }

            StdOut.println("\n Centro(s) Encontrado(s)");
            StdOut.println("Centro(s) da Árvore 1: " + Arrays.toString(analysis1.getCenters()));
            StdOut.println("Centro(s) da Árvore 2: " + Arrays.toString(analysis2.getCenters()));

            StdOut.println("\nCodificação Canônica");
            String encoding1 = analysis1.getCanonicalEncoding();
            String encoding2 = analysis2.getCanonicalEncoding();
            StdOut.println("Árvore 1: " + encoding1);
            StdOut.println("Árvore 2: " + encoding2);

            StdOut.println("\n Veredito Final");
            if (encoding1.equals(encoding2)) {
                StdOut.println("-> As árvores SÃO isomorfas.");
            } else {
                StdOut.println("-> As árvores NÃO são isomorfas.");
            }

        } catch (Exception e) {
            StdOut.println("Erro durante a execução. Verifique se os arquivos (" + caminhoArquivo1 + " e " + caminhoArquivo2 + ") existem e estão no formato correto.");
            e.printStackTrace();
        }
    }
}