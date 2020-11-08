package nl.cge.example.javabatch.control;

import nl.cge.example.javabatch.entity.Batchopdracht;
import nl.cge.example.javabatch.entity.BatchopdrachtRegel;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Stream;

@Stateless
public class VerwerkBatchopdrachtRegelService {

    @PersistenceContext(name = "pu-java-batch-example")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void verwerk(Stream<String> opdrachtregels, Batchopdracht batchopdracht) {
        opdrachtregels.map(opdrachtregel -> new BatchopdrachtRegel(opdrachtregel, batchopdracht))
                .forEach(batchopdrachtRegel -> em.persist(batchopdrachtRegel));
    }

}
