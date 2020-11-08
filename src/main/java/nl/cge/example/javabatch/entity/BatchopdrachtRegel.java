package nl.cge.example.javabatch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class BatchopdrachtRegel {

    enum Status { INITIAL, SUCCESS}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.INITIAL;

    @ManyToOne
    private Batchopdracht batchopdracht;

    private String opdrachtRegel;

    public BatchopdrachtRegel(String opdrachtregel, Batchopdracht batchopdracht) {
        this.opdrachtRegel = opdrachtregel;
        this.batchopdracht = batchopdracht;
    }

}
