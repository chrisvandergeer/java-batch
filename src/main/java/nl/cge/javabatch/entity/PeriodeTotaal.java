package nl.cge.javabatch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Data
@NoArgsConstructor
@Entity
public class PeriodeTotaal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate begindatum;
    private LocalDate einddatum;
    private BigDecimal totaalBedrag;

    public PeriodeTotaal(LocalDate datum) {
        begindatum = datum.with(TemporalAdjusters.firstDayOfMonth());
        einddatum = datum.with(TemporalAdjusters.lastDayOfMonth());
        totaalBedrag = BigDecimal.ZERO;
    }

    public void addBedrag(BigDecimal bedrag) {
        totaalBedrag = totaalBedrag.add(bedrag);
    }
}
