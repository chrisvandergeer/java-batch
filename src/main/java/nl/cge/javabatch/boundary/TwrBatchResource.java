package nl.cge.javabatch.boundary;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.control.JobExecutionDtoMapper;
import nl.cge.javabatch.entity.JobExecutionDto;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Properties;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static nl.cge.javabatch.entity.TijdWerkRegistratie.QRY_DATUM_OUDSTE_GEREGISTREERDE;

@Log4j2
@Stateless
@Consumes("application/json")
@Produces("application/json")
@Path("twrbatch")
public class TwrBatchResource {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @Inject
    private JobExecutionDtoMapper jobExecutionDtoMapper;

    @GET
    public Response getJobInfo() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        List<JobExecutionDto> jobExecutionDtoList = jobOperator.getJobNames().stream()
                .flatMap(jobName -> jobOperator.getJobInstances(jobName, 0, 25).stream())
                .map(jobInstance -> jobOperator.getJobExecution(jobInstance.getInstanceId()))
                .map(jobExecution -> jobExecutionDtoMapper.map(jobExecution))
                .collect(toList());
        return Response.ok(jobExecutionDtoList).build();
    }

    @POST
    public Response startBatch() {
        LocalDate oudsteTeVerwerkenDatum = entityManager.createNamedQuery(QRY_DATUM_OUDSTE_GEREGISTREERDE, LocalDate.class).getSingleResult();

        Properties jobProperties = new Properties();
        jobProperties.setProperty("begindatum", oudsteTeVerwerkenDatum.with(TemporalAdjusters.firstDayOfMonth()).toString());
        jobProperties.setProperty("einddatum", oudsteTeVerwerkenDatum.with(TemporalAdjusters.lastDayOfMonth()).toString());

        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long batchjobId = jobOperator.start("twrBatchjob", jobProperties);
        return getJobInfo();
    }

    @Path("stop/{executionId}")
    @POST
    public Response stopBatch(@PathParam("executionId") Long executionId) {
        BatchRuntime.getJobOperator().stop(executionId);
        return getJobInfo();
    }

}
