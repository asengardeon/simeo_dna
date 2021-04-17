package br.com.battisti.simeos.controllers;

import br.com.battisti.simeos.dna.DNAValidator;
import br.com.battisti.simeos.models.DNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNAController {

    @Autowired
    private DNAValidator dnaValidator;

    @PostMapping("/simian")
    boolean isSimianDNA(@RequestBody DNA dna) {
        if (dnaValidator.isSimian(dna.getDna())){
            return true;
        }
        throw new NotSimianException("DNA não pertence a um simeo");
    }
}
