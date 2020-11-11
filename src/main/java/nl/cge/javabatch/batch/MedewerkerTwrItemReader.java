package nl.cge.javabatch.batch;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.entity.TijdWerkRegistratie;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Properties;

import static nl.cge.javabatch.entity.TijdWerkRegistratie.*;

@Log4j2
@Named
public class MedewerkerTwrItemReader extends AbstractItemReader {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    JobContext jobContext;

    @Override
    public Object readItem() throws Exception {

        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        LocalDate begindatum = LocalDate.parse(jobParameters.getProperty("begindatum"));
        LocalDate einddatum = LocalDate.parse(jobParameters.getProperty("einddatum"));

        TijdWerkRegistratie tijdWerkRegistratie = entityManager.createNamedQuery(QRY_BY_STATUS_GEREGISTREERD_AND_DATUMRANGE, TijdWerkRegistratie.class)
                .setParameter("begindatum", begindatum)
                .setParameter("einddatum", einddatum)
                .setMaxResults(1)
                .getResultList().stream().findAny().orElse(null);

        return tijdWerkRegistratie;
    }
}
