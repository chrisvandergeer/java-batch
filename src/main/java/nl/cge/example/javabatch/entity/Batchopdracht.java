package nl.cge.example.javabatch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Batchopdracht {

    enum Status { INITIAL, RUNNABLE, RUNNING, SUCCES, FAILED }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.INITIAL;

    private String filename;

    private Timestamp uploadmoment;

    public Batchopdracht(String filename) {
        this.filename = filename;
    }

    @PrePersist
    public void onPrePersist() {
        this.uploadmoment = Timestamp.valueOf(LocalDateTime.now());
    }

}
