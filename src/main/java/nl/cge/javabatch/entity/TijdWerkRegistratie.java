package nl.cge.javabatch.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NamedQueries(value = {
        @NamedQuery(name = TijdWerkRegistratie.TWR_FIND_OUDSTE_GEREGISTREERD,
                query = "select t from TijdWerkRegistratie t " +
                        "where t.status = nl.cge.javabatch.entity.TijdWerkRegistratie.Status.GEREGISTREERD " +
                        "and t.datum = (select min(it.datum) " +
                        "from TijdWerkRegistratie it " +
                        "where it.status = nl.cge.javabatch.entity.TijdWerkRegistratie.Status.GEREGISTREERD)")
})
public class TijdWerkRegistratie {

    public static final String TWR_FIND_OUDSTE_GEREGISTREERD = "TijdWerkRegistratie.findOudsteGeregistreerd";

    enum Status {GEREGISTREERD, GOEDGEKEURD, UITBETAALD}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate datum;

    private Integer gewerkteUren;

    @ManyToOne
    private Medewerker medewerker;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.GEREGISTREERD;

}
