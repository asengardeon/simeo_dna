package br.com.battisti.simeos.data.entities;

import java.io.Serializable;

public class StatRetornoEntity implements Serializable {

    private String tipo;
    private int qtd;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
