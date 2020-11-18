package nl.cge.mybatch.control;

import lombok.extern.log4j.Log4j2;
import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j2
@Named
public class MyBatchUppercaseItemReader extends AbstractItemReader {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Override
    public Object readItem() throws Exception {
        Persoon persoon = entityManager.createQuery("select p from Persoon p where p.naam = " +
                "(select upper(pp.naam) from Persoon pp where p.id = pp.id)", Persoon.class)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findAny().orElse(null);
        log.info(persoon);
        return persoon;
    }
}
