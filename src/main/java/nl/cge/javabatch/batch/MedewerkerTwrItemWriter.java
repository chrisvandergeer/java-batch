package nl.cge.javabatch.batch;

import nl.cge.javabatch.entity.PeriodeTotaal;
import nl.cge.javabatch.entity.TijdWerkRegistratie;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
public class MedewerkerTwrItemWriter extends AbstractItemWriter {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    JobContext jobContext;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        items.stream()
                .map(obj -> (TijdWerkRegistratie) obj)
                .forEach(twr -> entityManager.persist(twr));

        PeriodeTotaal periodeTotaal = (PeriodeTotaal) jobContext.getTransientUserData();

        if (periodeTotaal.getId() == null) {
            entityManager.persist(periodeTotaal);
        } else {
            entityManager.merge(periodeTotaal);
        }

        entityManager.flush();
    }
}
