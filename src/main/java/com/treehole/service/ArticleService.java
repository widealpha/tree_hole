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

    public Result allArticles(){
        return Result.data(articleDao.getAllArticles());
    }

    public Result getArticle(int articleId){
        return Result.data(articleDao.getArticle(articleId));
    }

    public Result getArticlesByUserId(int userId){
        return Result.data(articleDao.getArticlesByUserId(userId));
    }

    public Result isAuthor(int userId, int articleId){
        int author = articleDao.getAuthor(articleId);
        return Result.data(String.valueOf(author == userId));
    }

    public Result addArticle(int userId, Article article){
        return Result.data(String.valueOf(articleDao.addArticle(
                userId, article.getTitle(), article.getContent(), article.getEmotion()) > 0));
    }

    public Result updateArticle(int userId, Article article){
        int author = articleDao.getAuthor(article.getId());
        if (userId != author && userDao.isAdmin(userId) == 0){
            return Result.error("您不能编辑此篇文章");
        }
        return Result.data(String.valueOf(articleDao.editArticle(article) > 0));
    }

    public Result deleteArticle(int userId, Article article){
        int author = articleDao.getAuthor(article.getId());
        if (userId != author && userDao.isAdmin(userId) == 0){
            return Result.error("您不能删除此篇文章");
        }
        return Result.data(String.valueOf(articleDao.deleteArticle(article.getId()) > 0));
    }

    public Result likeArticle(int userId, int articleId){
        JSONArray likeArray = JSON.parseArray(userDao.getLikeArticles(userId));
        if (likeArray == null){
            likeArray = new JSONArray();
        }
        if (likeArray.contains(articleId)){
            return Result.error("您已经点过赞了");
        }
        likeArray.add(articleId);
        userDao.setLikeArticles(userId, likeArray.toJSONString());
        articleDao.likeArticle(articleId);
        return Result.data("点赞成功");
    }

    public Result cancelLikeArticle(int userId, int articleId){
        JSONArray likeArray = JSON.parseArray(userDao.getLikeArticles(userId));
        if (likeArray == null){
            likeArray = new JSONArray();
        }
        if (!likeArray.contains(articleId)){
            return Result.error("您还没点过赞了");
        }
        likeArray.remove(new Integer(articleId));
        userDao.setLikeArticles(userId, likeArray.toJSONString());
        articleDao.cancelLikeArticle(articleId);
        return Result.data("取消点赞成功");
    }

    public Result disLikeArticle(int userId, int articleId){
        JSONArray dislikeArray = JSON.parseArray(userDao.getDislikeArticles(userId));
        if (dislikeArray == null){
            dislikeArray = new JSONArray();
        }
        if (dislikeArray.contains(articleId)){
            return Result.error("您已经踩过了");
        }
        dislikeArray.add(articleId);
        userDao.setDisLikeArticles(userId, dislikeArray.toJSONString());
        articleDao.dislikeArticle(articleId);
        return Result.data("踩了");
    }

    public Result cancelDislikeArticle(int userId, int articleId){
        JSONArray dislikeArray = JSON.parseArray(userDao.getDislikeArticles(userId));
        if (dislikeArray == null){
            dislikeArray = new JSONArray();
        }
        if (!dislikeArray.contains(articleId)){
            return Result.error("未点过踩");
        }
        dislikeArray.remove(new Integer(articleId));
        userDao.setDisLikeArticles(userId, dislikeArray.toJSONString());
        articleDao.cancelDislikeArticle(articleId);
        return Result.data("取消点踩成功");
    }
}
