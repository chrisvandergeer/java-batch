package nl.cge.mybatch.control;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
public class PersoonItemWriter extends AbstractItemWriter {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        items.forEach(p -> entityManager.persist(p));
    }
}
