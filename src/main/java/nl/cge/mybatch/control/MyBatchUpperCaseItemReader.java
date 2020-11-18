package nl.cge.mybatch.control;

import lombok.extern.log4j.Log4j2;
import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j2
@Named
public class MyBatchUpperCaseItemReader extends AbstractItemReader {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Override
    public Object readItem() throws Exception {
        return entityManager.createNamedQuery(Persoon.QRY_FIND_UPPERCASE_NAMEN, Persoon.class)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findAny().orElse(null);
    }
}
