package nl.cge.javabatch.control;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.util.List;

@Named
public class MedewerkerTwrItemWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List<Object> items) throws Exception {

    }
}
