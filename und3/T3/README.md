# Trabalho Prático 3 - O Problema do Criador de Problemas (UVA 10092)

## Informações Gerais
*   **Problema:** The Problem with the Problem Setter
*   **Link do Problema:** [UVA 10092](https://onlinejudge.org/external/100/10092.pdf)
*   **Integrantes do Grupo:** [Nomes dos Integrantes]
*   **Linguagem Utilizada:** Python 3

## Como Executar a Solução
1. Certifique-se de ter o Python 3 instalado.
2. O código principal deve ser executado via terminal, lendo a entrada padrão (stdin).
3. Comando sugerido: `python3 main.py < entradas_do_problema.txt`

## Explicação da Modelagem como Rede de Fluxo
O problema foi modelado como uma rede de fluxo bipartida para resolver a alocação de problemas em categorias respeitando as restrições de demanda e exclusividade.

### Definição dos Componentes da Rede:
*   **Origem (Source - S):** Um vértice super-fonte que inicia o fluxo.
*   **Sorvedouro (Sink - T):** Um vértice super-sumidouro onde todo o fluxo converge.
*   **Vértices de Problemas (P_j):** Representam cada um dos problemas disponíveis no banco.
*   **Vértices de Categorias (C_i):** Representam as categorias da prova.

### Arestas e Capacidades:
1.  **S &rarr; P_j:** Arestas da fonte para cada problema com **capacidade 1**. Isso garante que cada problema seja usado no máximo uma vez.
2.  **P_j &rarr; C_i:** Arestas de um problema para uma categoria se ele for elegível para ela. A **capacidade é 1**, permitindo que o problema flua para uma categoria específica.
3.  **C_i &rarr; T:** Arestas de cada categoria para o sumidouro com **capacidade c_i** (demanda da categoria). Isso impõe que a categoria receba exatamente o número necessário de problemas.

## Algoritmo Utilizado
Utilizamos o algoritmo **Edmonds-Karp**, que é uma implementação do método Ford-Fulkerson usando **Busca em Largura (BFS)** para encontrar caminhos aumentantes. 

### Papel do Grafo Residual
O grafo residual é fundamental para rastrear as capacidades restantes em tempo real. Ele permite o uso de arestas reversas, possibilitando que o algoritmo desfaça alocações sub-ótimas feitas em iterações anteriores para encontrar o fluxo máximo global.

## Conversão do Fluxo para a Resposta
Após encontrar o fluxo máximo:
1. Verificamos se o valor do fluxo máximo é igual à soma de todas as demandas das categorias.
2. Se for igual, imprimimos '1' e, para cada categoria, listamos os IDs dos problemas cujas arestas $P_j \to C_i$ no grafo residual ficaram com capacidade zero (indicando que o fluxo passou por elas).
3. Caso contrário, imprimimos '0'.

## Análise de Complexidade
A complexidade do Edmonds-Karp é **O(V &middot; E²)**. No contexto deste problema:
*   $V = 2 + N_p + N_k$
*   $E = N_p + \text{Total de relações Problema-Categoria} + N_k$
Dadas as restrições ($N_k \le 20, N_p \le 1000$), a solução é eficiente e executa dentro do tempo limite.

## Casos Especiais
*   **Inviabilidade:** Quando o banco de problemas não possui questões suficientes para cobrir as demandas de todas as categorias, o fluxo máximo será menor que a demanda total.
*   **Problemas Multicategoria:** A modelagem lida naturalmente com problemas que pertencem a várias categorias, garantindo a escolha de apenas uma através da restrição na aresta de entrada da fonte.

## Comprovação de Accepted
*(Espaço para imagem/link comprovando o Accepted na plataforma Online Judge)*
![Accepted Status](evidencias/accepted.png)
