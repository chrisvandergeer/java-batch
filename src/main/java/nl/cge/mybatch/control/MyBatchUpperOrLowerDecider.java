package nl.cge.mybatch.control;

import lombok.extern.log4j.Log4j2;

import javax.batch.api.Decider;
import javax.batch.runtime.StepExecution;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Log4j2
@Named
public class MyBatchUpperOrLowerDecider implements Decider {

    @Inject
    JobContext jobContext;

    @Override
    public String decide(StepExecution[] executions) throws Exception {
        log.info("Upper or Lower: {}", jobContext.getProperties().getProperty("whatIsTheCase"));
        return jobContext.getProperties().getProperty("whatIsTheCase");
    }
}
