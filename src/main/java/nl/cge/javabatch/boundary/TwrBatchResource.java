package nl.cge.javabatch.boundary;

import lombok.extern.log4j.Log4j2;
import nl.cge.javabatch.entity.JobExecutionDto;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Log4j2
@Stateless
@Consumes("application/json")
@Produces("application/json")
@Path("twrbatch")
public class TwrBatchResource {

    @GET
    public Response getJobInfo() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        List<JobExecutionDto> jobExecutionDtoList = jobOperator.getJobNames().stream()
                .flatMap(jobName -> jobOperator.getJobInstances(jobName, 0, 25).stream())
                .map(jobInstance -> jobOperator.getJobExecution(jobInstance.getInstanceId()))
                .map(jobExecution -> createJobInfo(jobExecution))
                .collect(toList());
        return Response.ok(jobExecutionDtoList).build();
    }

    private JobExecutionDto createJobInfo(JobExecution jobExecution) {
        JobExecutionDto dto = new JobExecutionDto();
        dto.setJobName(jobExecution.getJobName());
        dto.setExecutionId(jobExecution.getExecutionId());
        dto.setCreateTime(jobExecution.getCreateTime());
        dto.setStartTime(jobExecution.getStartTime());
        dto.setEndTime(jobExecution.getEndTime());
        dto.setLastUpdatedTime(jobExecution.getLastUpdatedTime());
        dto.setBatchStatus(jobExecution.getBatchStatus().toString());
        dto.setExitStatus(jobExecution.getExitStatus());
        Map<String, String> jobParameters = jobExecution.getJobParameters().entrySet().stream()
                .collect(toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        dto.setJobParameters(jobParameters);
        return dto;
    }

    @POST
    public Response startBatch() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long batchjobId = jobOperator.start("twrBatchjob", new Properties());
        return getJobInfo();
    }
}
