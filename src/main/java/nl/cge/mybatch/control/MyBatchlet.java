package nl.cge.mybatch.control;

import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class MyBatchlet extends AbstractBatchlet {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    JobContext jobContext;

    @Override
    public String process() throws Exception {
        boolean isNamenInLowercase = entityManager.createNamedQuery(Persoon.QRY_FIND_LOWERCASE_NAMEN)
                .setMaxResults(1)
                .getResultList().stream().findAny().isPresent();
        String caseStatus = isNamenInLowercase ? "lower" : "UPPER";
        jobContext.getProperties().setProperty("whatIsTheCase", caseStatus);
        return BatchStatus.COMPLETED.name();
    }
}
