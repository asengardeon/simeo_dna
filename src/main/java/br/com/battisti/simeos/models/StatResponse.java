package br.com.battisti.simeos.models;

public class StatResponse {

    //{"count_mutant_dna": 40, "count_human_dna": 100: "ratio": 0.4}

    private  Long count_mutant_dna;
    private Long count_human_dna;
    private float ratio;

    public StatResponse() {
        this.count_mutant_dna = 0L;
        this.count_human_dna = 0L;
        this.ratio = 0.0F;
    }

    public Long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(Long count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public Long getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(Long count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
