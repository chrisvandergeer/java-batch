package nl.cge.mybatch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = Persoon.QRY_FIND_UPPERCASE_NAMEN,
                query = "select p from Persoon p where p.naam = " +
                        "(select upper(pp.naam) from Persoon pp where p.id = pp.id)"
        ),
        @NamedQuery(
                name = Persoon.QRY_FIND_LOWERCASE_NAMEN,
                query = "select p from Persoon p where p.naam = " +
                        "(select lower(pp.naam) from Persoon pp where p.id = pp.id)"
        )
})
public class Persoon {

    public static final String QRY_FIND_UPPERCASE_NAMEN = "Persoon.findUpperCaseNamen";
    public static final String QRY_FIND_LOWERCASE_NAMEN = "Persoon.findLowercaseNamen";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String naam;

    public Persoon(String naam) {
        this.naam = naam;
    }
}
