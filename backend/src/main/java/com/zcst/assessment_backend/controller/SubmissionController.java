package com.zcst.assessment_backend.controller;

import com.zcst.assessment_backend.model.Submission;
import com.zcst.assessment_backend.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 考核登记接口。
 * 前端考生端提交表单 -> POST /api/submissions
 * 管理端查看记录   -> GET  /api/submissions
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    /**
     * 健康检查，用于确认后端服务是否启动成功。
     */
    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "assessment-backend"
        );
    }

    /**
     * 提交登记表单。
     */
    @PostMapping("/submissions")
    public ResponseEntity<Submission> submit(@RequestBody Submission submission) {
        if (submission.getName() == null || submission.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(submissionService.create(submission));
    }

    /**
     * 查询全部提交记录（管理端使用）。
     */
    @GetMapping("/submissions")
    public List<Submission> list() {
        return submissionService.listAll();
    }
}
