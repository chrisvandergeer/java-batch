package nl.cge.mybatch.control;

import nl.cge.mybatch.entity.Persoon;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class PersoonToUpperCaseProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        Persoon persoon = (Persoon) item;
        persoon.setNaam(persoon.getNaam().toUpperCase());
        return persoon;
    }
}
