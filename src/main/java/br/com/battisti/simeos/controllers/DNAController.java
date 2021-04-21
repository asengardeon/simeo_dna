package br.com.battisti.simeos.controllers;

import br.com.battisti.simeos.data.dao.StatsDAO;
import br.com.battisti.simeos.dna.DNAValidator;
import br.com.battisti.simeos.models.DNA;
import br.com.battisti.simeos.models.StatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNAController {

    @Autowired
    private DNAValidator dnaValidator;

    @Autowired
    private StatsDAO statsDAO;

    @PostMapping("/simian")
    boolean isSimianDNA(@RequestBody DNA dna) {
        if (dnaValidator.isSimian(dna.getDna())){
            return true;
        }
        throw new NotSimianException("DNA não pertence a um simeo");
    }

    @GetMapping("/stats")
    StatResponse getStat() {
        return statsDAO.getStat();
    }
}
