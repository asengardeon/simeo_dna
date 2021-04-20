package br.com.battisti.simeos.models;

import java.io.Serializable;

public class DNA implements Serializable {

    public static final String SIMIAN = "SIMIAN";
    public static final String HUMAN = "HUMAN";

    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
