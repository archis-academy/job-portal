package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.JobDto;
import com.archisacademy.jobportal.loggers.messages.JobAppMessage;
import com.archisacademy.jobportal.services.JobAppService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobAppService jobAppService;

    public JobController(JobAppService jobAppService) {
        this.jobAppService = jobAppService;
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobDto jobDto) {
        String responseMessage = jobAppService.createJob(jobDto);
        if (responseMessage.equals(JobAppMessage.JOB_CREATED_SUCCESS)) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long jobId, @RequestBody JobDto jobDto) {
        String responseMessage = jobAppService.updateJob(jobId, jobDto);
        if (responseMessage.equals(JobAppMessage.JOB_UPDATED_SUCCESS)) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {
        String responseMessage = jobAppService.deleteJob(jobId);
        if (responseMessage.equals(JobAppMessage.JOB_DELETED_SUCCESS)) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long jobId) {
        JobDto jobDto = jobAppService.getJobById(jobId);
        if (jobDto != null) {
            return ResponseEntity.ok(jobDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<JobDto>> getAllJobs(@RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<JobDto> jobs = jobAppService.getAllJobs(pageNo, pageSize);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/{jobId}/apply")
    public ResponseEntity<String> applyToJob(@PathVariable Long jobId, @RequestParam String userUuid) {
        String responseMessage = jobAppService.applyToJob(jobId, userUuid);
        if (responseMessage.equals(JobAppMessage.JOB_APPLIED_SUCCESS)) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }
}