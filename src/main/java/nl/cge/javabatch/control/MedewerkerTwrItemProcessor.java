package nl.cge.javabatch.control;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class MedewerkerTwrItemProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        return item;
    }
}
