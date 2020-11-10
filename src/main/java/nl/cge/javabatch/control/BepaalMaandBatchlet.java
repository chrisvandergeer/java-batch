package nl.cge.javabatch.control;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.entity.PeriodeTotaal;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

import static nl.cge.javabatch.entity.TijdWerkRegistratie.QRY_DATUM_OUDSTE_GEREGISTREERDE;

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
        LocalDate oudsteTeVerwerkenDatum = entityManager.createNamedQuery(QRY_DATUM_OUDSTE_GEREGISTREERDE, LocalDate.class).getSingleResult();
        log.info("Oudste datum : " + oudsteTeVerwerkenDatum);
        jobContext.getProperties().setProperty("oudsteTeVerwerkenDatum", oudsteTeVerwerkenDatum.toString());
        jobContext.setTransientUserData(new PeriodeTotaal(oudsteTeVerwerkenDatum));
        log.info("BepaalMaandBatchlet finished");
        return BatchStatus.COMPLETED.toString();
    }
}
