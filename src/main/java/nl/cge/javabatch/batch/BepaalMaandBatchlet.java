package nl.cge.javabatch.batch;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.entity.PeriodeTotaal;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Properties;

@Log4j2
@Named
public class BepaalMaandBatchlet extends AbstractBatchlet {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    JobContext jobContext;

    @Override
    public String process() throws Exception {
        log.info("BepaalMaandBatchlet started");

        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        LocalDate begindatum = LocalDate.parse(jobParameters.getProperty("begindatum"));
        LocalDate einddatum = LocalDate.parse(jobParameters.getProperty("einddatum"));

        jobContext.setTransientUserData(readOrCreatePeriodeTotaal(begindatum, einddatum));

        log.info("BepaalMaandBatchlet finished");

        return BatchStatus.COMPLETED.toString();
    }

    private PeriodeTotaal readOrCreatePeriodeTotaal(LocalDate beginDatum, LocalDate einddatum) {
        return entityManager.createNamedQuery(PeriodeTotaal.QRY_FIND_BY_BEGINDATUM, PeriodeTotaal.class)
                .setParameter("begindatum", beginDatum)
                .getResultList().stream().findAny().orElseGet(() -> new PeriodeTotaal(beginDatum, einddatum));
    }
}
