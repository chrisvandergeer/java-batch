package nl.cge.javabatch.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@NamedQueries(value = {
        @NamedQuery(name = Medewerker.QRY_BY_MEDEWERKERSNUMMER,
                query = "select m from Medewerker m where m.medewerkersnummer = :medewerkersnummer"),
        @NamedQuery(
                name = Medewerker.QRY_BY_DATERANGE_STATUS_GEREGISTREERD,
                query = "select m from TijdWerkRegistratie t join t.medewerker m " +
                        "where t.datum >= :begindatum " +
                        "and t.datum <= :einddatum " +
                        "and t.status = nl.cge.javabatch.entity.TijdWerkRegistratie.Status.GEREGISTREERD")
})
public class Medewerker {

    public static final String QRY_BY_MEDEWERKERSNUMMER = "Medewerker.findByMedewerkersnummer";
    public static final String QRY_BY_DATERANGE_STATUS_GEREGISTREERD = "TijdwerkRegistratie.findByDaterangeAndStatusGeregistreerd";

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
