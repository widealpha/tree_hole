package com.treehole.service;

import com.treehole.dao.ReportDao;
import com.treehole.dao.UserDao;
import com.treehole.domain.Report;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    ReportDao reportDao;
    @Autowired
    UserDao userDao;

    public Result addReport(Report report){
        return Result.data(reportDao.addReport(report) > 0);
    }

    public Result allReports(int userId){
        if (userDao.isAdmin(userId) <= 0){
            return Result.error("不是管理员");
        }
        return Result.data(reportDao.allReports());
    }

    public Result deleteReport(int userId, int reportId){
        if (userDao.isAdmin(userId) <= 0){
            return Result.error("不是管理员");
        }
        return Result.data(reportDao.deleteReport(reportId) > 0);
    }
}
