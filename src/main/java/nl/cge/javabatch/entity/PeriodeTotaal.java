package nl.cge.javabatch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = PeriodeTotaal.QRY_FIND_BY_BEGINDATUM,
                query = "select p from PeriodeTotaal p where p.begindatum = :begindatum"
        )
})
public class PeriodeTotaal {

    public static final String QRY_FIND_BY_BEGINDATUM = "PeriodeTotaal.findByBegindatum";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate begindatum;
    private LocalDate einddatum;
    private BigDecimal totaalBedrag;

    public PeriodeTotaal(LocalDate begindatum, LocalDate einddatum) {
        this.begindatum = begindatum;
        this.einddatum = einddatum;
        totaalBedrag = BigDecimal.ZERO;
    }

    public void addBedrag(BigDecimal bedrag) {
        totaalBedrag = totaalBedrag.add(bedrag);
    }
}
