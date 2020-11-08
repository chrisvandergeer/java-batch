package nl.cge.common;

import nl.cge.javabatch.entity.Medewerker;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

@Startup
@Singleton
public class InitializationBean {

    private static Random random = new Random(new Date().getTime());

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("Init started");
        createMedewerkers();
        System.out.println("Init ready");
    }

    private void createMedewerkers() {
        IntStream.range(1, 1000).forEach(i -> {
            String medewerkersnummer = String.format("%06d", i);
            Integer uurloon = randomUurloon();
            Medewerker medewerker = new Medewerker(medewerkersnummer, uurloon);
            entityManager.persist(medewerker);
        });
    }

    public Integer randomUurloon() {
        int result = random.nextInt(100);
        if (result < 40) {
            return randomUurloon();
        }
        return result;
    }
}
