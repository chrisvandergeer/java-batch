package nl.cge.mybatch.control;

import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
public class MyBatchItemWriter extends AbstractItemWriter {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        items.stream()
                .map(item -> (Persoon) item)
                .forEach(persoon -> entityManager.persist(persoon));
    }
}
