package nl.cge.mybatch.control;

import lombok.extern.log4j.Log4j2;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

@Log4j2
@Named
public class MyBatchlet extends AbstractBatchlet {

    @Override
    public String process() throws Exception {
        log.info("Hello Java Batch");
        return BatchStatus.COMPLETED.name();
    }
}
