package nl.cge.mybatch.control;

import lombok.extern.log4j.Log4j2;
import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j2
@Named
public class MyBatchlet extends AbstractBatchlet {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    private JobContext jobContext;

    @Override
    public String process() throws Exception {
        boolean isUpperCase = entityManager.createNamedQuery(Persoon.QRY_FIND_UPPERCASE_NAMEN, Persoon.class)
                .setMaxResults(1)
                .getResultList().stream().findAny().isPresent();
        String whatIsTheCase = isUpperCase ? "UPPER" : "lower";
        jobContext.getProperties().setProperty("whatIsTheCase", whatIsTheCase);
        return BatchStatus.COMPLETED.name();
    }
}
