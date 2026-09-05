package com.zcst.assessment_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 人员信息测试接口。
 * 数据只保存在内存中，服务重启后会清空。
 */
@RestController
@RequestMapping("/api/applications")
@CrossOrigin
public class ApplicationController {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<ApplicationRecord> records = new CopyOnWriteArrayList<>();

    @PostMapping
    public Map<String, Object> create(@RequestBody ApplicationRequest request) {
        if (isBlank(request.employeeNo()) || isBlank(request.name()) || isBlank(request.direction())) {
            return Map.of(
                    "success", false,
                    "message", "人员编号、姓名和岗位方向不能为空"
            );
        }

        ApplicationRecord record = new ApplicationRecord(
                idGenerator.getAndIncrement(),
                request.employeeNo().trim(),
                request.name().trim(),
                request.direction().trim(),
                LocalDateTime.now().toString()
        );
        records.add(record);

        return Map.of(
                "success", true,
                "message", "提交成功",
                "data", record
        );
    }

    @GetMapping
    public List<ApplicationRecord> list() {
        return new ArrayList<>(records);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public record ApplicationRequest(String employeeNo, String name, String direction) {
    }

    public record ApplicationRecord(Long id, String employeeNo, String name, String direction, String createdAt) {
    }
}
