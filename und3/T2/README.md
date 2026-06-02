Problema: Almost Shortest Path
Link: https://onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=3296
Integrantes: Nome 1, Nome 2, Nome 3
Linguagem: Java
Como executar:
  javac Main.java
  java Main < input.txt
Modelagem:
  Grafo direcionado e ponderado
  Vértices = estados
  Arestas = caminhos com custo
  Representado por lista de adjacência
Algoritmo:
  Dijkstra (2 vezes)
  Primeiro para achar menor caminho
  Depois para remover arestas do menor caminho
Variação do Dijkstra:
  Uso de fila de prioridade (heap)
  Armazena múltiplos predecessores
  Segunda execução ignora arestas removidas
Complexidade:
  Tempo: O((V + E) log V)
  Espaço: O(V + E)
