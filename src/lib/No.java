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

    
    public No(T valor){
        this.valor = valor;
        this.noDireita = null;
        this.noEsquerda = null;
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
    
    
}
