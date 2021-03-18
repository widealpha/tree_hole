package com.treehole.dao;

import com.treehole.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {
    @Insert("INSERT INTO article_table (author, title, content, emotion, createTime, lastEditTime, images)" +
            " VALUES (#{author}, #{title},  #{content}, #{emotion}, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP, #{images})")
    Integer addArticle(int author, String title, String content, String emotion, String images);

    @Update("UPDATE article_table " +
            "SET title = #{title}, content = #{content}, emotion = #{emotion}, lastEditTime = CURRENT_TIME(), images = #{images}" +
            " WHERE id = #{id}")
    Integer editArticle(Article article);

    @Delete("DELETE FROM article_table WHERE id = #{id}")
    Integer deleteArticle(Integer id);

    @Select("SELECT * FROM article_table WHERE id = #{id} LIMIT 1")
    Article getArticle(Integer id);

    @Select("SELECT * FROM article_table WHERE verify = 0")
    List<Article> getAllUnVerifyArticle();

    @Update("UPDATE article_table SET verify = 1 WHERE  id = #{articleId}")
    Integer verifyArticle(int articleId);

    @Update("UPDATE article_table SET verify = 1 WHERE  verify = 0")
    Integer verifyAllArticle();

    @Select("SELECT * FROM article_table WHERE author = #{userId}")
    List<Article> getArticlesByUserId(int userId);

    @Select("SELECT * FROM article_table WHERE verify = 1")
    List<Article> getAllArticles();

    @Select("SELECT * FROM article_table WHERE (title LIKE '%${key}%' OR content LIKE '%${key}%' OR emotion LIKE '%${key}%') AND verify = 1")
    List<Article> searchArticle(String key);

    @Select("SELECT * FROM article_table WHERE author = #{userId} AND TO_DAYS(now()) - TO_DAYS(article_table.lastEditTime) <= #{days} AND verify = 1")
    List<Article> getAllArticleByTime(int userId, int days);

    @Select("SELECT * FROM article_table WHERE TO_DAYS(lastEditTime) >= TO_DAYS(#{start}) AND TO_DAYS(lastEditTime) <= TO_DAYS(#{end}) AND verify = 1")
    List<Article> getArticlesByStartEnd(String start, String end);

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
