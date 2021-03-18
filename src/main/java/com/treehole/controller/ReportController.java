package com.treehole.controller;

import com.treehole.domain.Report;
import com.treehole.service.ReportService;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @RequestMapping("reportArticle")
    Result reportArticle(@RequestParam int userId, @RequestParam int articleId, @RequestParam String reason) {
        Report report = new Report();
        report.setReportAuthor(userId);
        report.setArticleId(articleId);
        report.setReportType(0);
        report.setReason(reason);
        return reportService.addReport(report);
    }

    @RequestMapping("reportComment")
    Result reportComment(@RequestParam int userId, @RequestParam int commentId, @RequestParam String reason) {
        Report report = new Report();
        report.setReportAuthor(userId);
        report.setArticleId(commentId);
        report.setReportType(1);
        report.setReason(reason);
        return reportService.addReport(report);
    }

    @RequestMapping("deleteReport")
    Result deleteReport(@RequestParam int userId, @RequestParam int id) {
        return reportService.deleteReport(userId, id);
    }

    @RequestMapping("allReports")
    Result allReports(@RequestParam int userId) {
        return reportService.allReports(userId);
    }
}
