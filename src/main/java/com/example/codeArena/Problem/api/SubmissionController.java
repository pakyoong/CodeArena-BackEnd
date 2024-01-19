package com.example.codeArena.Problem.api;

import com.example.codeArena.Problem.dto.SubmissionDto;
import com.example.codeArena.Problem.service.SubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // 새로운 제출 생성
    @PostMapping
    public ResponseEntity<SubmissionDto> createSubmission(@RequestBody SubmissionDto submissionDto) {
        try {
            SubmissionDto createdSubmission = submissionService.createSubmission(submissionDto);
            return ResponseEntity.ok(createdSubmission);
        } catch (Exception e) {
            logger.error("제출 처리 중 예외 발생", e);
            return ResponseEntity.badRequest().build();
        }
    }

    // 특정 사용자의 제출 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getSubmissionsByUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(submissionService.getSubmissionsByUser(userId));
        } catch (Exception e) {
            logger.error("사용자 제출 조회 중 예외 발생", e);
            return ResponseEntity.badRequest().build();
        }
    }

    // 특정 문제에 대한 제출 조회
    @GetMapping("/problem/{problemId}")
    public ResponseEntity<?> getSubmissionsByProblem(@PathVariable Long problemId) {
        try {
            return ResponseEntity.ok(submissionService.getSubmissionsByProblem(problemId));
        } catch (Exception e) {
            logger.error("문제 제출 조회 중 예외 발생", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
