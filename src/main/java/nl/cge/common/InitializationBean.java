package nl.cge.common;

import nl.cge.javabatch.entity.Medewerker;
import nl.cge.javabatch.entity.TijdWerkRegistratie;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

import static java.time.temporal.TemporalAdjusters.*;

@Startup
@Singleton
public class InitializationBean {

    private static final int AANTAL_MEDEWERKERS = 500;
    private static final LocalDate START_DATM = LocalDate.now().minusMonths(3).with(firstDayOfMonth()).minusDays(1);
    private static final Random RANDOM = new Random(new Date().getTime());

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("Init started");
        createMedewerkers();
        createTijdWerkRegistratie();
        System.out.println("Init ready");
    }

    private void createTijdWerkRegistratie() {
        IntStream.range(1, AANTAL_MEDEWERKERS).forEach(i -> {
            String medewerkersnummer = String.format("%06d", i);
            Medewerker medewerker = entityManager.createNamedQuery(Medewerker.QRY_FIND_BY_MEDEWERKERSNUMMER, Medewerker.class)
                    .setParameter("medewerkersnummer", medewerkersnummer)
                    .getSingleResult();
            LocalDate twrDatum = getNextWorkingDay(START_DATM);
            while (twrDatum.isBefore(LocalDate.now())) {
                TijdWerkRegistratie twr = new TijdWerkRegistratie();
                twr.setDatum(twrDatum);
                twr.setGewerkteUren(randomGewerkteUren());
                twr.setMedewerker(medewerker);
                entityManager.persist(twr);
                twrDatum = getNextWorkingDay(twrDatum);
            }
        });
    }

    private LocalDate getNextWorkingDay(LocalDate today) {
        LocalDate nextDay = today.plusDays(1);
        String strDayOfWeek = nextDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        if (strDayOfWeek.equals("Saturday") || strDayOfWeek.equals("Sunday")) {
            return getNextWorkingDay(nextDay);
        }
        return nextDay;
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
        int result = RANDOM.nextInt(100);
        if (result < 40) {
            return randomUurloon();
        }
        return result;
    }

    private Integer randomGewerkteUren() {
        return RANDOM.nextInt(10);
    }
}
