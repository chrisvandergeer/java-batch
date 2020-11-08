package nl.cge.example.javabatch.control;

import nl.cge.example.javabatch.entity.Batchopdracht;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CreeerBatchopdrachtService {

    @PersistenceContext(name = "pu-java-batch-example")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Batchopdracht creeer(String filename) {
        Batchopdracht batchopdracht = new Batchopdracht(filename);
        em.persist(batchopdracht);
        em.flush();
        return batchopdracht;
    }
}
