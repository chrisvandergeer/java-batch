package nl.cge.mybatch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Persoon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String naam;

    public Persoon(String naam) {
        this.naam = naam;
    }
}
