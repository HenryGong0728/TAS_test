package com.zcst.assessment_backend.service;

import com.zcst.assessment_backend.model.Submission;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 提交记录服务（考核版）。
 * 使用内存 Map 存储数据，替代数据库；服务重启后数据清空。
 */
@Service
public class SubmissionService {

    private final Map<Long, Submission> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public SubmissionService() {
        // 预置一条示例数据，方便管理端启动后直接看到表格效果
        Submission sample = new Submission();
        sample.setId(idGenerator.incrementAndGet());
        sample.setName("张三");
        sample.setStudentId("2026001");
        sample.setDirection("前端");
        sample.setPhone("13800000000");
        sample.setNote("示例数据，考核时可忽略");
        sample.setCreatedAt(LocalDateTime.now());
        store.put(sample.getId(), sample);
    }

    /**
     * 保存一条提交记录。
     */
    public Submission create(Submission submission) {
        submission.setId(idGenerator.incrementAndGet());
        submission.setCreatedAt(LocalDateTime.now());
        store.put(submission.getId(), submission);
        return submission;
    }

    /**
     * 查询全部提交记录（按 ID 倒序，最新提交在前）。
     */
    public List<Submission> listAll() {
        List<Submission> list = new ArrayList<>(store.values());
        list.sort(Comparator.comparing(Submission::getId).reversed());
        return list;
    }
}
