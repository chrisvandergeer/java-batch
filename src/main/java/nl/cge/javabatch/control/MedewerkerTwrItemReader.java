package nl.cge.javabatch.control;

import lombok.extern.log4j.Log4j2;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;

@Log4j2
@Named
public class MedewerkerTwrItemReader extends AbstractItemReader {

    @Inject
    JobContext jobContext;

    @Override
    public Object readItem() throws Exception {
        String oudsteDatum = jobContext.getProperties().getProperty("oudsteDatum", "noppes");
        log.info("ReadItem() Oudeste datum: " + oudsteDatum);
        LocalDate datum = (LocalDate) jobContext.getTransientUserData();
        log.info(datum);
        return null;
    }

}
