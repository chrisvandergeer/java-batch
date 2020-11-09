package nl.cge.javabatch.control;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.entity.TijdWerkRegistratie;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static nl.cge.javabatch.entity.TijdWerkRegistratie.*;

@Log4j2
@Named
public class BepaalMaandBatchlet extends AbstractBatchlet {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Override
    public String process() throws Exception {
        log.info("Batchjob started");
        List<TijdWerkRegistratie> resultList = entityManager.createNamedQuery(TWR_FIND_OUDSTE_GEREGISTREERD, TijdWerkRegistratie.class)
                .getResultList();
        log.info("Aantal : " + resultList.size());
        resultList.forEach(log::info);
        return BatchStatus.COMPLETED.toString();
    }
}
