package nl.cge.javabatch.entity;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class JobExecutionDto {
    private String jobName;
    private Long executionId;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private Date lastUpdatedTime;
    private String batchStatus;
    private String exitStatus;
    private Map<String, String> jobParameters = new HashMap<>();
}
