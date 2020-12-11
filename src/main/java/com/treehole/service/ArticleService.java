package com.treehole.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.treehole.dao.ArticleDao;
import com.treehole.dao.UserDao;
import com.treehole.domain.Article;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    public Result allArticles() {
        return Result.data(articleDao.getAllArticles());
    }

    public Result getArticle(int articleId) {
        articleDao.addArticleReadCount(articleId);
        return Result.data(articleDao.getArticle(articleId));
    }

    public Result getAllUnVerifyArticles(int userId){
        if (userDao.isAdmin(userId) > 0){
            return Result.data(articleDao.getAllUnVerifyArticle());
        }
        return Result.error("不是管理员");
    }

    public Result verifyArticle(int userId, int id){
        if (userDao.isAdmin(userId) > 0){
            return Result.data(articleDao.verifyArticle(id));
        }
        return Result.error("不是管理员");
    }

    public Result getArticlesByUserId(int userId) {
        return Result.data(articleDao.getArticlesByUserId(userId));
    }

    public Result searchArticle(String key){
        return Result.data(articleDao.searchArticle(key));
    }

    public Result getArticleByTime(int userId, int days){
        return Result.data(articleDao.getAllArticleByTime(userId, days));
    }

    public Result getArticleByStartEnd(String startTime, String endTime){
        return Result.data(articleDao.getArticlesByStartEnd(startTime, endTime));
    }

    public Result isAuthor(int userId, int articleId) {
        Integer author = articleDao.getAuthor(articleId);
        if (author == null){
            return Result.data(false);
        }
        return Result.data(String.valueOf(author == userId));
    }

    public Result addArticle(int userId, Article article) {
        return Result.data(String.valueOf(articleDao.addArticle(
                userId, article.getTitle(), article.getContent(), article.getEmotion(), article.getImages()) > 0));
    }

    public Result updateArticle(int userId, Article article) {
        int author = articleDao.getAuthor(article.getId());
        if (userId != author && userDao.isAdmin(userId) == 0) {
            return Result.error("您不能编辑此篇文章");
        }
        return Result.data(String.valueOf(articleDao.editArticle(article) > 0));
    }

    public Result deleteArticle(int userId, int id) {
        int author = articleDao.getAuthor(id);
        if (userId != author && userDao.isAdmin(userId) == 0) {
            return Result.error("您不能删除此篇文章");
        }
        return Result.data(String.valueOf(articleDao.deleteArticle(id) > 0));
    }

    public Result likeArticle(int userId, int articleId) {
        boolean success = articleDao.likeArticle(articleId) > 0;
        if (success) {

            JSONArray likeArray = JSON.parseArray(userDao.getLikeArticles(userId));
            if (likeArray == null) {
                likeArray = new JSONArray();
            }
            if (likeArray.contains(articleId)) {
                return Result.error("您已经点过赞了");
            }
            likeArray.add(articleId);
            userDao.setLikeArticles(userId, likeArray.toJSONString());

        }
        return Result.data(success);
    }

    public Result cancelLikeArticle(int userId, int articleId) {
        boolean success = articleDao.cancelLikeArticle(articleId) > 0;
        if (success) {
            JSONArray likeArray = JSON.parseArray(userDao.getLikeArticles(userId));
            if (likeArray == null) {
                likeArray = new JSONArray();
            }
            if (!likeArray.contains(articleId)) {
                return Result.error("您还没点过赞了");
            }
            likeArray.remove(new Integer(articleId));
            userDao.setLikeArticles(userId, likeArray.toJSONString());
        }
        return Result.data(success);
    }

    public Result disLikeArticle(int userId, int articleId) {
        boolean success = articleDao.dislikeArticle(articleId) > 0;
        if (success) {
            JSONArray dislikeArray = JSON.parseArray(userDao.getDislikeArticles(userId));
            if (dislikeArray == null) {
                dislikeArray = new JSONArray();
            }
            if (dislikeArray.contains(articleId)) {
                return Result.error("您已经踩过了");
            }
            dislikeArray.add(articleId);
            userDao.setDisLikeArticles(userId, dislikeArray.toJSONString());
        }
        return Result.data(success);
    }

    public Result cancelDislikeArticle(int userId, int articleId) {
        boolean success = articleDao.cancelDislikeArticle(articleId) > 0;
        if (success) {
            JSONArray dislikeArray = JSON.parseArray(userDao.getDislikeArticles(userId));
            if (dislikeArray == null) {
                dislikeArray = new JSONArray();
            }
            if (!dislikeArray.contains(articleId)) {
                return Result.error("未点过踩");
            }
            dislikeArray.remove(new Integer(articleId));
            userDao.setDisLikeArticles(userId, dislikeArray.toJSONString());
        }
        return Result.data(success);
    }
}
