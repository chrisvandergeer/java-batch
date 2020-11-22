package nl.cge.mybatch.boundary;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import nl.cge.mybatch.entity.Persoon;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Log4j2
@Stateless
@Consumes("application/json")
@Produces("application/json")
@Path("mybatch")
public class MyBatchResource {

    @PersistenceContext(name = "pu-java-batch")
    private EntityManager entityManager;

    @GET
    public Response getJobInfo() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        List<JobExecutionDto> jobExecutionDtoList = jobOperator.getJobNames().stream()
                .flatMap(jobName -> jobOperator.getJobInstances(jobName, 0, 7).stream())
                .map(jobInstance -> jobOperator.getJobExecution(jobInstance.getInstanceId()))
                .map(JobExecutionDto::map)
                .collect(toList());
        return Response.ok(jobExecutionDtoList).build();
    }

    @Path("persoon")
    @GET
    public Response findPersonen() {
        List<Persoon> result = entityManager.createQuery("select p from Persoon p", Persoon.class)
                .setMaxResults(10).getResultList();
        return Response.ok(result).build();
    }

    @Data
    static class JobExecutionDto {
        private String jobName;
        private Long executionId;
        private String createTime;
        private String startTime;
        private String endTime;
        private String lastUpdatedTime;
        private String batchStatus;
        private String exitStatus;
        private Map<String, String> jobParameters = new HashMap<>();

        public static JobExecutionDto map(JobExecution jobExecution) {
            JobExecutionDto dto = new JobExecutionDto();
            dto.setJobName(jobExecution.getJobName());
            dto.setExecutionId(jobExecution.getExecutionId());
            dto.setCreateTime(jobExecution.getCreateTime().toString());
            dto.setStartTime(jobExecution.getStartTime().toString());
            dto.setEndTime(jobExecution.getEndTime().toString());
            dto.setLastUpdatedTime(jobExecution.getLastUpdatedTime().toString());
            dto.setBatchStatus(jobExecution.getBatchStatus().toString());
            dto.setExitStatus(jobExecution.getExitStatus());
            Map<String, String> jobParameters = jobExecution.getJobParameters().entrySet().stream()
                    .collect(toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
            dto.setJobParameters(jobParameters);
            return dto;
        }
    }
}


