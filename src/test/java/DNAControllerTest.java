import br.com.battisti.simeos.SimeosApplication;
import br.com.battisti.simeos.data.repositories.StatRepository;
import br.com.battisti.simeos.models.DNA;
import br.com.battisti.simeos.models.StatResponse;
import configs.H2Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import utils.FileUtils;

@SpringBootTest(classes = {SimeosApplication.class, H2Configuration.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DNAControllerTest {

    private final FileUtils files;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StatRepository statRepository;


    public DNAControllerTest() {
        this.files = new FileUtils();
    }

    @BeforeEach
    public void setup(){
        statRepository.deleteAll();
    }


    @Test
    public void testIsEmptyStat(){
        StatResponse result = this.restTemplate.getForObject("http://localhost:" + port + "/stats", StatResponse.class);
        Assertions.assertEquals(result.getCount_human_dna(), 0);
        Assertions.assertEquals(result.getCount_mutant_dna(), 0);
        Assertions.assertEquals(result.getRatio(), 0);
    }

    @Test
    public void testStatWithZeroHumans(){
        postFile("simeo_curto.txt");
        StatResponse result = this.restTemplate.getForObject("http://localhost:" + port + "/stats", StatResponse.class);
        Assertions.assertEquals(result.getCount_human_dna(), 0);
        Assertions.assertEquals(result.getCount_mutant_dna(), 1);
        Assertions.assertEquals(result.getRatio(), 100);
    }

    @Test
    public void testStatWithSimianEqualHumans(){
        postFile("simeo_curto.txt");
        postFile("humano_curto.txt");
        StatResponse result = this.restTemplate.getForObject("http://localhost:" + port + "/stats", StatResponse.class);
        Assertions.assertEquals(result.getCount_human_dna(), 1);
        Assertions.assertEquals(result.getCount_mutant_dna(), 1);
        Assertions.assertEquals(result.getRatio(), 100);
    }

    @Test
    public void testStatWithSimianLowerThanHumans(){
        postFile("simeo_curto.txt");
        postFile("humano_curto.txt");
        postFile("humano.txt");
        StatResponse result = this.restTemplate.getForObject("http://localhost:" + port + "/stats", StatResponse.class);
        Assertions.assertEquals(result.getCount_human_dna(), 2);
        Assertions.assertEquals(result.getCount_mutant_dna(), 1);
        Assertions.assertEquals(result.getRatio(), 50);
    }


    @Test
    public void testIsSimean(){
        String result = postFile("simeo_curto.txt");
        Assertions.assertEquals(result, "true");
    }

    @Test
    public void testIsHuman(){
        String result = postFile("humano_curto.txt");
        Assertions.assertNotEquals(result, "true");
    }

    private String postFile(String s) {
        String[] dnaData = files.readFileLines(s);
        DNA dna = new DNA();
        dna.setDna(dnaData);
        HttpEntity<DNA> requestEntity = new HttpEntity<>(dna);
        return this.restTemplate.postForObject("http://localhost:" + port + "/simian", requestEntity, String.class);
    }


}
