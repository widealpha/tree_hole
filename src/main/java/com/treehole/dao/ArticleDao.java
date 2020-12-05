package com.treehole.dao;

import com.treehole.domain.Article;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface ArticleDao {
    @Insert("INSERT INTO article_table (author, title, content, emotion, createTime, lastEditTime)" +
            " VALUES (#{author}, #{title},  #{content}, #{emotion}, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP)")
    Integer addArticle(int author, String title, String content, String emotion);

    @Update("UPDATE article_table " +
            "SET title = #{title}, content = #{content}, emotion = #{emotion}, lastEditTime = CURRENT_TIME()" +
            " WHERE id = #{id}")
    Integer editArticle(Article article);

    @Delete("DELETE FROM article_table WHERE id = #{id}")
    Integer deleteArticle(Integer id);

    @Select("SELECT * FROM article_table WHERE id = #{id} LIMIT 1")
    Article getArticle(Integer id);

    @Select("SELECT * FROM article_table WHERE author = #{userId}")
    List<Article> getArticlesByUserId(int userId);


    @Select("SELECT * FROM article_table")
    List<Article> getAllArticles();

    @Select("SELECT * FROM article_table WHERE title LIKE '%${key}%' OR content LIKE '%${key}%' OR emotion LIKE '%${key}%'")
    List<Article> searchArticle(String key);

    @Select("SELECT * FROM article_table WHERE TO_DAYS(now()) - TO_DAYS(article_table.lastEditTime) <= #{days}")
    List<Article> getAllArticleByTime(int days);

    @Select("SELECT `like` FROM article_table WHERE id = #{id} LIMIT 1")
    Integer getArticleLikeCount(Integer id);

    @Select("SELECT `dislike` FROM article_table WHERE id = #{id} LIMIT 1")
    Integer getArticleDislikeCount(Integer id);

    @Select("SELECT `readCount` FROM article_table WHERE id = #{id} LIMIT 1")
    Integer getArticleReadCount(Integer id);

    @Update("UPDATE article_table SET readCount = readCount + 1 WHERE id = #{id}")
    Integer addArticleReadCount(Integer id);

    @Update("UPDATE article_table SET `like` = `like` + 1 WHERE id = #{id}")
    Integer likeArticle(Integer id);

    @Update("UPDATE article_table SET `like` = `like` - 1 WHERE id = #{id}")
    Integer cancelLikeArticle(Integer id);

    @Update("UPDATE article_table SET `dislike` = `dislike` + 1 WHERE id = #{id}")
    Integer dislikeArticle(Integer id);

    @Update("UPDATE article_table SET `dislike` = `dislike` - 1 WHERE id = #{id}")
    Integer cancelDislikeArticle(Integer id);

    @Select("SELECT author FROM article_table WHERE id = #{id}")
    Integer getAuthor(int id);
}
