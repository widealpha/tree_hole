package com.treehole.dao;

import com.treehole.domain.Article;
import com.treehole.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {

    @Select("SELECT * FROM comment_table ORDER BY create_time DESC")
    List<Comment> getAllComments();

    @Select("SELECT id, article_id, comment_id, content, agree, disagree, create_time FROM comment_table WHERE article_id = #{articlrId}")
    List<Comment> getCommentsByArticleId(int articleId);

    @Select("SELECT id, article_id, comment_id, content, agree, disagree, create_time FROM comment_table WHERE comment_id = #{commentId}")
    List<Comment> getCommentsByCommentId(int commentId);

    @Select("SELECT id, article_id, comment_id, content, agree, disagree, create_time FROM comment_table WHERE id = #{id}")
    Comment getCommentById(int id);

    @Select("SELECT * FROM comment_table WHERE author = #{userId}")
    List<Comment> getMyComments(int userId);

    @Select("SELECT author FROM comment_table WHERE id = #{id}")
    Integer getAuthor(int id);

    @Delete("DELETE FROM comment_table WHERE id = #{id}")
    int deleteComment(int id);

    @Insert("INSERT INTO comment_table (author, article_id, comment_id, content, create_time) " +
            "VALUES (#{author}, #{articleId}, #{commentId}, #{content}, CURRENT_TIMESTAMP())")
    int addComment(Comment comment);

    @Update("UPDATE comment_table SET agree = agree + 1 WHERE id = #{id}")
    int agreeComment(int id);

    @Update("UPDATE comment_table SET agree = agree - 1 WHERE id = #{id}")
    int cancelAgreeComment(int id);

    @Update("UPDATE comment_table SET disagree = disagree + 1 WHERE id = #{id}")
    int disagreeComment(int id);

    @Update("UPDATE comment_table SET disagree = disagree - 1 WHERE id = #{id}")
    int cancelDisagreeComment(int id);

}
