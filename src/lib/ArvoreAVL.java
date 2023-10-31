package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    private No<T> raiz;

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    // Move o filho esquerdo do nó para a direita, e o filho direito para a esquerda
    private No<T> rotacaoDireita(No<T> no) {
        No<T> noAux = no.getNoEsquerda();
        no.setNoEsquerda(no.getNoDireita());
        noAux.setNoDireita(no);

        no.attFatorBalanceamento();
        noAux.attFatorBalanceamento();

        return noAux;
    }

    // Move o filho direito do nó para a esquerda e o filho esquerdo para a direita
    private No<T> rotacaoEsquerda(No<T> no) {
        No<T> noAux = no.getNoDireita();
        no.setNoDireita(no.getNoEsquerda());
        noAux.setNoEsquerda(no);

        no.attFatorBalanceamento();
        noAux.attFatorBalanceamento();

        return noAux;
    }

    // Realiza uma rotação esquerda no filho esquerdo do no, em seguida uma rotação
    // direita no próprio nó
    private No<T> rotacaoEsquedaDireita(No<T> no) {
        no.setNoEsquerda(rotacaoEsquerda(no.getNoEsquerda()));
        return rotacaoDireita(no);
    }

    // Realiza uma rotação direita no filho direito do no, em seguida uma rotação
    // esquerda no proprio nó
    private No<T> rotacaoDireitaEsqueda(No<T> no) {
        no.setNoDireita(rotacaoDireita(no.getNoDireita()));
        return rotacaoEsquerda(no);
    }

    @Override
    public void adicionar(T novoValor) {

        // Se a arvore estiver vazia, cria um novo No e ele será a raiz
        if (this.raiz == null) {
            this.raiz = new No<T>(novoValor);
        }

        // adiciona o novo
        raiz = adicionar(raiz, novoValor);

        // Agora precisamos balancear o novo valor inserido para manter o fator de
        // balanceamento

        // O loop ira acontecer até que o fator de balanceamento seja até no máximo 1
        while (raiz.getFatorBalanceamento() > 1 || raiz.getFatorBalanceamento() < -1) {
            // Se o fator de balanceamento for 2, a arvore esta desbalanceada para direita
            if (raiz.getFatorBalanceamento() > 1) {
                //Verifica se o no esquedo esta desbalanceado a esquerda
                if(raiz.getNoEsquerda().getFatorBalanceamento() < 0) {
                    raiz = rotacaoDireitaEsqueda(raiz);
                } else {
                    raiz = rotacaoDireita(raiz);
                }
            } else {
                if (raiz.getNoDireita().getFatorBalanceamento() > 0 ){
                    raiz = rotacaoEsquedaDireita(raiz);
                } else {
                    raiz = rotacaoEsquerda(raiz);
                }
            }
        }

    }

    private No<T> adicionar(No<T> no, T valor) {
        No<T> novoNo = new No<T>(valor);
        int comparacao = comparador.compare(novoNo.getValor(), no.getValor());

        if (comparacao < 0) {
            if (no.getNoEsquerda() == null) {
                no.setNoEsquerda(novoNo);
            } else {
                no.setNoEsquerda(adicionar(no.getNoDireita(), valor));
            }
        } else {
            if (no.getNoDireita() == null) {
                no.setNoDireita(novoNo);
            } else {
                no.setNoDireita(adicionar(novoNo, valor));
            }
        }

        no.attFatorBalanceamento();
        return no;
    }

    @Override
    public T remover (T valor) {
        T busca = pesquisar(valor);

        //caso não encontre retorna null
        if (busca == null) {
            return busca;
        }        
        No<T> no = new No<T>(busca);
        //Se o no a ser removido tiver apensa um filho, o filho deve subistituir
        if(no.getNoEsquerda() == null || no.getNoDireita() == null) {
            No<T> filho = no.getNoEsquerda() != null ? no.getNoEsquerda() : no.getNoEsquerda();

            if (filho != null) {
                filho.setPai(no.getPai()); 
            }

            if (comparador.compare(no.getValor(), raiz.getValor()) == 0) {
                raiz = filho;
            } else {
                if (comparador.compare(no.getValor(), no.getPai().getNoEsquerda().getValor()) == 0) {
                    no.getPai().setNoEsquerda(filho);
                } else {
                    no.getPai().setNoDireita(filho);
                }
            }

            while (no != null) {
                no.attFatorBalanceamento();
                no = no.getPai();
            }
        }
        // } else {
        //     //Se o nó a ser removido tem dois filhos, o sucessor é o filho que esta na ordem
        //     No<t> sucessor = no.
        // }
        return raiz.getValor();
    }

    // private No<T> remover (No<T> no, T valor) {

    // }


}