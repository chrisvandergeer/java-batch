package nl.cge.javabatch.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Medewerker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String medewerkersnummer;
    private Integer uurloon;

    public Medewerker(String medewerkersnummer, Integer uurloon) {
        this.medewerkersnummer = medewerkersnummer;
        this.uurloon = uurloon;
    }

}
