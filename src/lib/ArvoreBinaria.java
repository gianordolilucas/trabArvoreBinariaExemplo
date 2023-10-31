package lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    private No<T> raiz;
    private No<T> atual = null;
    protected Comparator<T> comparador; 
    private ArrayList<No<T>> pilhaNavegacao = null;

    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }

    @Override
    public void adicionar(T novoValor) {
        if (raiz == null) {
            raiz = new No<T>(novoValor);
        }
    
        adicionar(raiz, novoValor, true);
    }
    
    private No<T> adicionar( No<T> no, T novoValor) {
    
        if (no == null) {
            return new No<T>(novoValor);
        }

        int comparacao = comparador.compare(novoValor, no.getValor());

        if (comparacao < 0) {
            no.setNoEsquerda(adicionar(no.getNoEsquerda(), novoValor));
        } else if (comparacao > 0) {
            no.setNoDireita(adicionar(no.getNoDireita(), novoValor));
        }

        return no;
    }

    //Método adicionar sem recursividade para não dar erro de stack na primeira parte da questão 09 do questionário
    private  void adicionar (No<T> no, T novoValor, boolean noRec) {
        No<T> novoNo = new No<>(novoValor);
        No<T> noAtual = no;
        while (true) {
            int comparacao = comparador.compare(novoValor, noAtual.getValor());
            if (comparacao < 0) {
                if(noAtual.getNoEsquerda() == null) {
                    noAtual.setNoDireita(novoNo);
                    return;
                }
                noAtual = noAtual.getNoEsquerda();
            } else {
                if(noAtual.getNoDireita() == null) {
                    noAtual.setNoDireita(novoNo);
                    return;
                }
                noAtual = noAtual.getNoDireita();
            }
        }
    }
    

    @Override
    public T pesquisar(T valor) {
        return pesquisar(raiz, valor);
    }

    private T pesquisar(No<T> no, T valor) {
        int comparacao = comparador.compare(no.getValor(), valor);
        while( no  !=null && comparacao == 0) {
            if (comparacao < 0) {
                no = no.getNoEsquerda();
            } else {
                no = no.getNoEsquerda();
            }
        }
        return no.getValor();
    }  
    

    @Override
    public T remover(T valor) {
        raiz = remover(raiz, valor);
        if (raiz == null) {
            return null;
        }        
        return raiz.getValor();
    }

    private No<T> remover( No<T> no, T valor) {
        
        if (no == null) {
            return null; // Caso base: o valor não foi encontrado
        }

        int comparacao = comparador.compare(valor, no.getValor());

        if (comparacao < 0) {
            no.setNoEsquerda(remover(no.getNoEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setNoDireita(remover(no.getNoDireita(), valor));
        } else {
            // Nó com até um filho
            if (no.getNoEsquerda() == null) {
                return no.getNoDireita();
            } else if (no.getNoDireita() == null) {
                return no.getNoEsquerda();
            }

            // Nó com dois filhos diferente de null
            no.setValor(encontrarMenorValor(no.getNoDireita()));
            no.setNoDireita(remover(no.getNoDireita(), no.getValor()));
        }

        return no;

    }
    
    private T encontrarMenorValor(No<T> no) {
        T menorValor = no.getValor();
        while (no.getNoEsquerda() != null) {
            menorValor = no.getNoEsquerda().getValor();
            no = no.getNoEsquerda();
        }
        return menorValor;
    }

    @Override
    public int altura() {
        return altura(raiz, true);
    }

    private int altura(No<T> no) {
        if (no == null) {
            return -1; // Árvore vazia ou folha
        }
    
        int alturaEsquerda = altura(no.getNoEsquerda());
        int alturaDireita = altura(no.getNoDireita());
    
        // A altura da árvore é o máximo entre as alturas da subárvore esquerda e direita, mais 1 para contar o nó da raiz
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    private int altura(No<T> no, boolean noRec) {
        if (no == null){
            return 0; //Árvore vazia
        }


        int altura = 0;
        Queue<No<T>> fila = new LinkedList<>();
        fila.offer(no);

        while(true) {
            int tamanhoFila = fila.size();
            if (tamanhoFila == 0) {
                return altura;
            }

            altura++;

            while(tamanhoFila > 0) {
                No<T> noAtual = fila.poll();
                if(noAtual.getNoEsquerda() != null) {
                    fila.offer(noAtual.getNoEsquerda());
                }
                if(noAtual.getNoDireita() != null) {
                    fila.offer(noAtual.getNoDireita());
                }

                tamanhoFila--;
            }
        }
    }

    @Override
    public int quantidadeNos() {
        return quantidadeNos(raiz);
    }

    private int quantidadeNos(No<T> no) {
        if (no == null) {
            return 0; // Árvore sem raiz é vazia
        }

        int nosNaEsquerda = quantidadeNos(no.getNoEsquerda());
        int nosNaDireita = quantidadeNos(no.getNoDireita());

        // A quantidade de nós na árvore é a soma dos nós na subárvore esquerda, subárvore direita mais 1 do nó raiz 
        return nosNaEsquerda + nosNaDireita + 1;
    }

    @Override
    public String caminharEmNivel() {
        StringBuilder resultado = new StringBuilder();
        if (raiz == null) {
            return "[]"; // Árvore vazia
        }

        Queue<No<T>> fila = new LinkedList<>();
        fila.offer(raiz);

        resultado.append("[");
        while (!fila.isEmpty()) {
            No<T> no = fila.poll();
            resultado.append(no.getValor().toString());
            
            if (no.getNoEsquerda() != null) {
                fila.offer(no.getNoEsquerda());
            }
            if (no.getNoDireita() != null) {
                fila.offer(no.getNoDireita());
            }

            if (!fila.isEmpty()) {
                resultado.append("\n");
            }
        }
        resultado.append("]");

        return resultado.toString();
    }


    @Override
    public String caminharEmOrdem() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("[");

        caminharEmOrdem(raiz, resultado);

        resultado.append("]");
        return resultado.toString();
    }

    private void caminharEmOrdem(No<T> no, StringBuilder resultado) {
        if (no == null) {
            return;
        }

        caminharEmOrdem(no.getNoEsquerda(), resultado);
        resultado.append(no.getValor().toString());
        
        if (no.getNoDireita() != null) {
            resultado.append("\n");
        }

        caminharEmOrdem(no.getNoDireita(), resultado);
    }

    @Override
    public T obterProximo() {
        
        if (atual == null) {
            // Primeira chamada, encontre o nó mais à esquerda (menor elemento da árvore)
            No<T> no = raiz;

            while (no != null) {
                pilhaNavegacao.add(no);
                no = no.getNoEsquerda();
            }
        } else {
            No<T> no = atual.getNoDireita();
            // Coloca na pilha todos os nós à esquerda do nó mais à direita
            while (no != null) {
                pilhaNavegacao.add(no);
                no = no.getNoEsquerda();
            }
        }
        // Se a pilha de navegação tem algo, pegue o valor do nó no topo da pilha
        if (!pilhaNavegacao.isEmpty()) {
            atual = pilhaNavegacao.remove(pilhaNavegacao.size() - 1);
            return atual.getValor();
        }

        return null;
    }

    

    @Override
    public void reiniciarNavegacao() {
        this.atual = null;
        this.pilhaNavegacao.clear();
    }

}