package br.com.battisti.simeos.dna;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DNA {

    public static final int MINIMAL_COUNT = 4; //quantidade minica de caracteres na sequencia para identificar DNA
    public static final String[] ATCG_DATA = {"A", "T", "C", "G"};

    public boolean isSimian (String[] dna){
        if (isMinimumSizeDNA(dna) && isQuadraticMatrix(dna) && isValidCharacters(dna)){
            return isHorizontalSequenceForAllChar(dna) || isVerticalSequenceForAllChar(dna) || isDiagonalSequenceForAllChar(dna);
        }
        return false;
    }


    public boolean isQuadraticMatrix(String[] dna) {
        int size = dna.length;
        return Arrays.stream(dna).allMatch(s -> s.length() == size);
    }

    public boolean isValidCharacters(String[] dna) {
        return Arrays.stream(dna).allMatch(s -> s.toUpperCase().matches("[ATCG]+"));
    }

    public boolean isHorizontalSequence(String[] dna, String identifier) {
        if (dna.length >= MINIMAL_COUNT) {
            return Arrays.stream(dna).anyMatch(s -> s.contains(identifier.repeat(MINIMAL_COUNT)));
        }
        return false;
    }


    public boolean isVerticalSequenceForAllChar(String[] dna) {
        return Arrays.stream(ATCG_DATA).anyMatch(s-> isVerticalSequence(dna, s));
    }

    public boolean isHorizontalSequenceForAllChar(String[] dna) {
        return Arrays.stream(ATCG_DATA).anyMatch(s-> isHorizontalSequence(dna, s));
    }

    public boolean isDiagonalSequenceForAllChar(String[] dna) {
        return Arrays.stream(ATCG_DATA).anyMatch(s-> isDiagonalSequence(dna, s));
    }

    public boolean isVerticalSequence(String[] dna, String identifier) {
        if (dna.length >= MINIMAL_COUNT){
            String[] dnaTransformado = transformHorizontalVertical(dna);
            return Arrays.stream(dnaTransformado).anyMatch(s -> s.contains(identifier.repeat(MINIMAL_COUNT)));
        }
        return false;
    }

    private boolean getValueDiagonal(String[] dna, String identifier, int i, int j){
        boolean result = true;
        for(int k =0; k<4; k++){
            result = result && dna[i+k].charAt(j+k) == identifier.charAt(0);
        }
        if (!result){
            result = true;
            for(int k =0; k<4; k++){
                result = result && dna[i+k].charAt(j+3-k) == identifier.charAt(0);
            }
        }
        return result;
    }


    public boolean isDiagonalSequence(String[] dna, String identifier) {
        if (dna.length >= MINIMAL_COUNT) {
            String firstLine = dna[0];
            for (int i = 0; i < firstLine.length() - 4; i++) {
                for (int j = 0; j < firstLine.length() - 4; j++) {
                    if (getValueDiagonal(dna, identifier, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String[] transformHorizontalVertical(String[] dna) {
        String[] result = new String[dna[0].length()];
        for(int i = 0; i<dna[0].length();i++){
            String wordDna = "";
            for(String line: dna){
                String s = String.valueOf(line.toCharArray()[i]);
                wordDna = wordDna.concat(s);
            }
            result[i] = wordDna;
        }
        return result;
    }

    public boolean isMinimumSizeDNA(String[] simeoDna) {
        return simeoDna.length >= MINIMAL_COUNT && Arrays.stream(simeoDna).allMatch(s -> s.length()>=MINIMAL_COUNT);
    }
}


