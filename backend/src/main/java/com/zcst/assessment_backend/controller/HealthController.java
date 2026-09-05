package com.zcst.assessment_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 健康检查接口。
 * 前端测试页通过 GET /api/health 检测后端连接状态。
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class HealthController {

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
}
