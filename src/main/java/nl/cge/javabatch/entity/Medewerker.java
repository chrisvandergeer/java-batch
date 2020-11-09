package nl.cge.javabatch.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@NamedQueries(value = {
        @NamedQuery(name = Medewerker.QRY_FIND_BY_MEDEWERKERSNUMMER,
                query = "select m from Medewerker m where m.medewerkersnummer = :medewerkersnummer")
})
public class Medewerker {

    public static final String QRY_FIND_BY_MEDEWERKERSNUMMER = "Medewerker.findByMedewerkersnummer";
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
