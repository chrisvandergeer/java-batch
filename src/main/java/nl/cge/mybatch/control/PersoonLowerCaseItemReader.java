package nl.cge.mybatch.control;

import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class PersoonLowerCaseItemReader extends AbstractItemReader {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Override
    public Object readItem() throws Exception {
        return entityManager.createNamedQuery(Persoon.QRY_FIND_LOWERCASE_NAMEN, Persoon.class)
                .setMaxResults(1)
                .getResultList().stream().findAny().orElse(null);
    }
}
