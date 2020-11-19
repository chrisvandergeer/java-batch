package nl.cge.mybatch.control;

import javax.batch.api.Decider;
import javax.batch.runtime.StepExecution;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UpperOrLowerCaseDecider implements Decider {

    @Inject
    private JobContext jobContext;

    @Override
    public String decide(StepExecution[] executions) throws Exception {
        return jobContext.getProperties().getProperty("whatIsTheCase");
    }
}
