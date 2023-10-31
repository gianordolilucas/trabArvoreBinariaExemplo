/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author victoriocarvalho
 */
public class No<T> {

    private T valor;
    private No<T> noDireita;
    private No<T> noEsquerda;
    private int fatorBalanceamento;
    private No<T> pai;

    private int altura;

    public No(T valor) {
        this.valor = valor;
        this.noDireita = null;
        this.noEsquerda = null;
        this.pai = null;
        this.fatorBalanceamento = 0;
    }

    /**
     * @return the valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * @return the noDireita
     */
    public No<T> getNoDireita() {
        return noDireita;
    }

    /**
     * @param noDireita the noDireita to set
     */
    public void setNoDireita(No<T> noDireita) {
        this.noDireita = noDireita;
    }

    /**
     * @return the noEsquerda
     */
    public No<T> getNoEsquerda() {
        return noEsquerda;
    }

    /**
     * @param noEsquerda the noEsquerda to set
     */
    public void setNoEsquerda(No<T> noEsquerda) {
        this.noEsquerda = noEsquerda;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void attFatorBalanceamento() {
        this.fatorBalanceamento = this.fatorBalanceamento();
    }

    public int getFatorBalanceamento() {
        return fatorBalanceamento;
    }

      public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }

    /**
     * @return Altura da SubArvore a direita
     */
    public int obterAltura() {
        return this.obterAltura(this);
    }

    /**
     * @return Método recursivo para obter altura de uma arvore.
     *         Esse método percorre os nós buscando sempre a maior altura
     *         entre as subarvores da esqueda e direita
     */
    private int obterAltura(No<T> no) {
        if (no == null) {
            return -1;
        } else {
            int alturaEsquerda = obterAltura(no.getNoEsquerda());
            int alturaDireita = obterAltura(no.getNoDireita());
            if (alturaDireita > alturaEsquerda) {
                return alturaDireita + 1;
            } else {
                return alturaEsquerda + 1;
            }
        }
    }

    public int fatorBalanceamento() {
        return obterAltura(this.noDireita) - obterAltura(this.noEsquerda);
    }

}
