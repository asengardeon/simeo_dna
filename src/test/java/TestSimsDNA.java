import br.com.battisti.simeos.dna.DNA;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utils.FileUtils;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DNA.class)
public class TestSimsDNA {

    public static final String VALID_HORIZONTAL_CHAR = "C";
    public static final String VALID_VERTICAL_CHAR = "G";
    public static final String VALID_DIAGONAL_CHAR = "A";
    private final FileUtils files;
    public static final String[] SIM_DNA = new String[]{"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

    @Autowired
    private DNA dnaValidator;

    public TestSimsDNA() {
        this.files = new FileUtils();
    }

    @Test
    public void testIsSimian (){
        String[] dna = files.readFileLines("simeo_curto.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertTrue(isSimean);
    }

    @Test
    public void testIsSimianLongDNA (){
        String[] dna = files.readFileLines("simeo.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertTrue(isSimean);
    }

    @Test
    public void testIsSimianLongDNA_somente_vertical (){
        String[] dna = files.readFileLines("simeo_somente_vertical.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertTrue(isSimean);
    }

    @Test
    public void testIsSimianLongDNA_somente_horizontal (){
        String[] dna = files.readFileLines("simeo_somente_horizontal.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertTrue(isSimean);
    }

    @Test
    public void testIsSimianLongDNA_somente_diagonal (){
        String[] dna = files.readFileLines("simeo_somente_diagonal.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertTrue(isSimean);
    }

    @Test
    public void testIsHuman (){
        String[] dna = files.readFileLines("humano_curto.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertFalse(isSimean);
    }

    @Test
    public void testIsHuman2 (){
        String[] dna = files.readFileLines("humano2.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertFalse(isSimean);
    }

    @Test
    public void testIsHumanLongDNA (){
        String[] dna = files.readFileLines("humano.txt");
        boolean isSimean = dnaValidator.isSimian(dna);
        Assertions.assertFalse(isSimean);
    }

    @Test
    public void testSimeoTypeAExists_short(){
        String[] dna = files.readFileLines("simeo_curto.txt");
        Assertions.assertTrue(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeGExists_short(){
        String[] dna = files.readFileLines("simeo_curto.txt");
        Assertions.assertTrue(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeCExists_short(){
        String[] dna = files.readFileLines("simeo_curto.txt");
        Assertions.assertTrue(dnaValidator.isSimian(dna));
    }

    @Test
   public void testSimeoTypeAExists_long(){
        String[] dna = files.readFileLines("simeo.txt");
        Assertions.assertTrue(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeGExists_long(){
        String[] dna = files.readFileLines("simeo.txt");
        Assertions.assertTrue(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeCExists_long(){
        String[] dna = files.readFileLines("simeo.txt");
        Assertions.assertTrue(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeANotExists_short(){
        String[] dna = files.readFileLines("humano_curto.txt");
        Assertions.assertFalse(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeGNotExists_short(){
        String[] dna = files.readFileLines("humano_curto.txt");
        Assertions.assertFalse(dnaValidator.isSimian(dna));
    }

    @Test
    public void testSimeoTypeCNotExists_short(){
        String[] dna = files.readFileLines("humano_curto.txt");
        Assertions.assertFalse(dnaValidator.isSimian(dna));
    }

    @Test
    public void testVerifyIsQuadraticMatrix(){
        Assertions.assertTrue(dnaValidator.isQuadraticMatrix(SIM_DNA));
    }

    @Test
    public void testVerifyNotIsQuadraticMatrix(){
        String[] dna = {"CTGAGA", "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        Assertions.assertFalse(dnaValidator.isQuadraticMatrix(dna));
    }

    @Test
    public void testVerifyMinimumSizeDNA(){
        Assertions.assertTrue(dnaValidator.isMinimumSizeDNA(SIM_DNA));
    }

    @Test
    public void testVerifyMinimumSizeDNAGreater(){
        String[] dna = {"CTGAGA", "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        Assertions.assertTrue(dnaValidator.isMinimumSizeDNA(dna));
    }

    @Test
    public void testVerifyMinimumSizeDNALower(){
        String[] dna = {"CTG", "CTG", "CTG", "TAT", "AGA", "CCC", "TCA"};
        Assertions.assertFalse(dnaValidator.isMinimumSizeDNA(dna));
    }

    @Test
    public void testVerifyIsIrregularQuadratic(){
        String[] dna = {"CTGAGA", "CT", "TATTGT", "G", "CCCCTA", "TCTG"};
        Assertions.assertFalse(dnaValidator.isQuadraticMatrix(dna));
    }


    @Test
    public void testDNAisValidCharacters(){
        Assertions.assertTrue(dnaValidator.isValidCharacters(SIM_DNA));
    }

    @Test
    public void testDNAisInvalidCharacters(){
        String[] dna = {"CTGAGA", "CTGAXC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        Assertions.assertFalse(dnaValidator.isValidCharacters(dna));
    }

    @Test
    public void testDNAHorizontalSequenceDNAFalse(){
        Assertions.assertFalse(dnaValidator.isHorizontalSequence(SIM_DNA, VALID_VERTICAL_CHAR));
    }

    @Test
    public void testDNAHorizontalSequenceDNATrue(){
        Assertions.assertTrue(dnaValidator.isHorizontalSequence(SIM_DNA, VALID_HORIZONTAL_CHAR));
    }

    @Test
    public void testDNAVerticalSequenceDNAFalse(){
        Assertions.assertFalse(dnaValidator.isVerticalSequence(SIM_DNA, VALID_HORIZONTAL_CHAR));
    }

    @Test
    public void testDNAVerticalSequenceDNATrue(){
        Assertions.assertTrue(dnaValidator.isVerticalSequence(SIM_DNA, VALID_VERTICAL_CHAR));
    }

    @Test
    public void testDNADiagonalSequenceDNAFalse(){
        Assertions.assertFalse(dnaValidator.isDiagonalSequence(SIM_DNA, VALID_VERTICAL_CHAR));
    }

    @Test
    public void testDNADiagonalSequenceDNATrue(){
        Assertions.assertTrue(dnaValidator.isDiagonalSequence(SIM_DNA, VALID_DIAGONAL_CHAR));
    }

    @Test
    public void testDNADiagonal2SequenceDNATrue(){
        String[] dna = {"CAGAGA","CTATGC","TATAGT","AGAGAG","CCTCTA","TCACTG"};
        Assertions.assertTrue(dnaValidator.isDiagonalSequence(dna, VALID_DIAGONAL_CHAR));
    }

    @Test
    public void testDNATransformVerticalHorizontalNotEqual(){
        String[] dna2 = {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        Assertions.assertFalse(Arrays.deepEquals(dnaValidator.transformHorizontalVertical(SIM_DNA), dna2));
    }

    @Test
    public void testDNATransformVerticalHorizontalEqual(){
        String[] dna2 = {"CCTACT", "TTAGCC", "GATACA", "ATTGCC", "GGGGTT", "ACTGAG"};
        Assertions.assertTrue(Arrays.deepEquals(dnaValidator.transformHorizontalVertical(SIM_DNA), dna2));
    }






}
