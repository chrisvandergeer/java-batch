package nl.cge.javabatch.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class TijdWerkRegistratie {

    private enum Status { GEREGISTREERD, GOEDGEKEURD, UITBETAALD }

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
