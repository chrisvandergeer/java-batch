package nl.cge.mybatch.control;

import lombok.extern.log4j.Log4j2;

import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

@Log4j2
@Named
public class MyChunkStepListener implements StepListener {

    @Inject
    private StepContext stepContext;

    @Override
    public void beforeStep() throws Exception {
        log.info("Step {} started", stepContext.getStepName());
    }

    @Override
    public void afterStep() throws Exception {
        log.info("Step {} finished", stepContext.getStepName());
    }
}
