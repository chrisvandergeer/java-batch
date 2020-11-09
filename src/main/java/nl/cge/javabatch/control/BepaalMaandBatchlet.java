package nl.cge.javabatch.control;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.entity.TijdWerkRegistratie;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static nl.cge.javabatch.entity.TijdWerkRegistratie.*;

@Log4j2
@Named
public class BepaalMaandBatchlet extends AbstractBatchlet {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    JobContext jobContext;

    @Override
    public String process() throws Exception {
        log.info("Batchjob started");
        LocalDate oudsteTeVerwerkenDatum = entityManager.createNamedQuery(QRY_DATUM_OUDSTE_GEREGISTREERDE, LocalDate.class).getSingleResult();
        log.info("Oudste datum : " + oudsteTeVerwerkenDatum);
        jobContext.getProperties().setProperty("oudsteDatum", oudsteTeVerwerkenDatum.toString());
        jobContext.setTransientUserData(oudsteTeVerwerkenDatum);
        return BatchStatus.COMPLETED.toString();
    }
}
