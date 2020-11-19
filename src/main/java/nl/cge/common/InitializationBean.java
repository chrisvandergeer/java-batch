package nl.cge.common;

import lombok.extern.log4j.Log4j2;
import nl.cge.mybatch.entity.Persoon;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.IntStream;

@Startup
@Singleton
@Log4j2
public class InitializationBean {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    private NaamGenerator naamGenerator;

    @PostConstruct
    public void init() {
        log.info("Init started");
        IntStream.range(0, 1000).forEach(i -> {
            Persoon persoon = new Persoon(naamGenerator.genereerNaam().toUpperCase());
            entityManager.persist(persoon);
        });
        log.info("Init ready");
    }

}
