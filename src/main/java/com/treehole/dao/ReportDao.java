package com.treehole.dao;

import com.treehole.domain.Report;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportDao {
    @Select("SELECT * FROM report_table")
    List<Report> allReports();

    @Delete("DELETE FROM report_table WHERE id = #{id}")
    Integer deleteReport(int id);

    @Insert("INSERT INTO report_table (comment_id, article_id, report_author, report_type)" +
            " VALUES (#{commentId}, #{articleId}, #{reportAuthor}, #{reportType})")
    Integer addReport(Report report);
}
