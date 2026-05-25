nome do problema: Grid MST.

link do problema: https://open.kattis.com/problems/gridmst.

integrante(s) do grupo: Gustavo Leon.

Linguagem utilizada: Java.

como executar a solução: necessário a instalação do pacote algs4 via web se utilizar intelliJ.

explicação da modelagem do problema como grafo ponderado: Cada ponto do plano é um vértice do grafo e cada par de pontos forma uma aresta 

ponderada pela distância de Manhattan.

algoritmo utilizado: algorítmo de kruskal.

papel do Union-Find/DSU: Controla os componentes conectados do grafo e evita ciclos durante a construção da MST.

Complexidade da criação das arestas:	O(n²)

Complexidade da ordenação:	O(n²logn)

Complexidade total:	O(n²logn)

Complexidade de memória:	O(n²)

Caso especial 1:	Pontos repetidos geram arestas de peso zero.

Caso especial 2:	O algoritmo deve evitar ciclos ao selecionar arestas.

Caso especial 3:	Grafos muito grandes podem causar Time Limit Exceeded e Memory Limit Exceeded.

Caso especial 4:	Como o grafo é completo implícito, o número de arestas cresce quadraticamente.
