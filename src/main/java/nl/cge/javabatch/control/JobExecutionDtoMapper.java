package nl.cge.javabatch.control;

import nl.cge.javabatch.entity.JobExecutionDto;

import javax.batch.runtime.JobExecution;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class JobExecutionDtoMapper {

    public JobExecutionDto map(JobExecution jobExecution) {
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
}
