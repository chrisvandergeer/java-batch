package nl.cge.javabatch.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = TijdWerkRegistratie.QRY_DATUM_OUDSTE_GEREGISTREERDE,
                query = "select min(t.datum) from TijdWerkRegistratie t " +
                        "where t.status = nl.cge.javabatch.entity.TijdWerkRegistratie.Status.GEREGISTREERD"),
        @NamedQuery(
                name = TijdWerkRegistratie.QRY_BY_STATUS_GEREGISTREERD_AND_DATUMRANGE,
                query = "select t from TijdWerkRegistratie t " +
                        "where t.status = nl.cge.javabatch.entity.TijdWerkRegistratie.Status.GEREGISTREERD " +
                        "and t.datum >= :begindatum " +
                        "and t.datum <= :einddatum"
        )
})
public class TijdWerkRegistratie {

    public static final String QRY_DATUM_OUDSTE_GEREGISTREERDE = "TijdwerkRegistratie.datumOudsteGeregistreerde";
    public static final String QRY_BY_STATUS_GEREGISTREERD_AND_DATUMRANGE = "TijdWerkRegistratie.findByStatusGeregistreerdAndDatumRange";

    enum Status {GEREGISTREERD, UITBETAALD}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate datum;

    private Integer gewerkteUren;

    @ManyToOne
    private Medewerker medewerker;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.GEREGISTREERD;

    public void setStatusUitbetaald() {
        this.status = Status.UITBETAALD;
    }

    public BigDecimal berekenTeBetalenBedrag() {
        return new BigDecimal(gewerkteUren * medewerker.getUurloon());
    }
}
