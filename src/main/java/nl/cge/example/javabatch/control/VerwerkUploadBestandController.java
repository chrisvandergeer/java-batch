package nl.cge.example.javabatch.control;

import nl.cge.example.javabatch.entity.Batchopdracht;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class VerwerkUploadBestandController {

    @EJB
    private CreeerBatchopdrachtService creeerBatchopdrachtService;

    @EJB
    private VerwerkBatchopdrachtRegelService verwerkBatchopdrachtRegelService;

    public void verwerk(InputStream uploadbestand, String filename) {
        Stream<String> batchregels = new BufferedReader(new InputStreamReader(uploadbestand)).lines();
        Batchopdracht batchopdracht = creeerBatchopdrachtService.creeer(filename);
        verwerkBatchopdrachtRegelService.verwerk(batchregels, batchopdracht);
    }
}
