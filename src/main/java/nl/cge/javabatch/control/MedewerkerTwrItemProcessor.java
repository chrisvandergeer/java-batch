package nl.cge.javabatch.control;

import nl.cge.javabatch.entity.PeriodeTotaal;
import nl.cge.javabatch.entity.TijdWerkRegistratie;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MedewerkerTwrItemProcessor implements ItemProcessor {

    @Inject
    JobContext jobContext;

    @Override
    public Object processItem(Object item) throws Exception {
        TijdWerkRegistratie tijdWerkRegistratie = (TijdWerkRegistratie) item;

        PeriodeTotaal periodeTotaal = (PeriodeTotaal) jobContext.getTransientUserData();
        periodeTotaal.addBedrag(tijdWerkRegistratie.berekenTeBetalenBedrag());

        tijdWerkRegistratie.setStatusUitbetaald();
        return tijdWerkRegistratie;
    }
}
