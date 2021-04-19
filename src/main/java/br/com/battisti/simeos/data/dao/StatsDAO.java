package br.com.battisti.simeos.data.dao;

import br.com.battisti.simeos.data.entities.StatEntity;
import br.com.battisti.simeos.data.entities.StatRetornoEntity;
import br.com.battisti.simeos.data.repositories.StatRepository;
import br.com.battisti.simeos.models.StatResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.battisti.simeos.models.DNA.SIMIAN;

@Service
public class StatsDAO {

    private static final int TYPE_FIELD = 0;
    private static final int VALUE_FIELD = 1;

    @Autowired
    private StatRepository repo;

    @Autowired
    private EntityManager em;

    public void saveDna(String[] dna, String type){
        Optional<String> strDna = Arrays.stream(dna).reduce((s, s2) -> s.concat(s2));
        String sha256hex = DigestUtils.sha256Hex(strDna.get());
        StatEntity item = repo.findByKey(sha256hex);
        if(item == null){
            item = new StatEntity();
            item.setKey(sha256hex);
            item.setType(type);
        }
        item.setContent(strDna.get());
        repo.save(item);
    }

    public StatResponse getStat(){
        Query q = em.createQuery("select type as tipo, count(*) as qtd from StatEntity group by type");

        StatResponse result = new StatResponse();

        for (int i = 0; i < q.getResultList().size(); i++) {
            Object[] s = (Object[]) q.getResultList().get(i);
            if(s[TYPE_FIELD].equals(SIMIAN)){
                result.setCount_mutant_dna((Long) s[VALUE_FIELD]);
            }else{
                result.setCount_human_dna((Long) s[VALUE_FIELD]);
            }
        }

        float ratio;
        if (result.getCount_human_dna() == 0) {
            ratio = result.getCount_mutant_dna();
        }else{
            ratio = result.getCount_mutant_dna() / result.getCount_human_dna();
        }
        result.setRatio(ratio*100);

        return result;
    }
}
