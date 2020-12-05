package com.treehole.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.treehole.dao.CommentDao;
import com.treehole.dao.UserDao;
import com.treehole.domain.Comment;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;

    public Result getCommentsByArticleId(int articleId){
        return Result.data(commentDao.getCommentsByArticleId(articleId));
    }

    public Result getCommentsByCommentId(int commentId){
        return Result.data(commentDao.getCommentsByCommentId(commentId));
    }

    public Result getComment(int commentId){
        return Result.data(commentDao.getCommentById(commentId));
    }

    public Result myComments(int userId){
        return Result.data(commentDao.getMyComments(userId));
    }

    public Result isAuthor(int userId, int commentId){
        Integer author = commentDao.getAuthor(commentId);
        if (author == null){
            return Result.data(false);
        }
        return Result.data(String.valueOf(author == userId));
    }

    public Result addComment(Comment comment){
        return Result.data(String.valueOf(commentDao.addComment(comment) > 0));
    }

    public Result deleteComment(int userId, int id){
        int author = commentDao.getAuthor(id);
        if (userId != author && userDao.isAdmin(userId) == 0){
            return Result.error("您不能删除此评论");
        }
        return Result.data(String.valueOf(commentDao.deleteComment(id) > 0));
    }

    public Result likeComment(int userId, int commentId){
        boolean success = commentDao.agreeComment(commentId) > 0;
        if (success){
            JSONArray likeArray = JSON.parseArray(userDao.getLikeComments(userId));
            if (likeArray == null){
                likeArray = new JSONArray();
            }
            if (likeArray.contains(commentId)){
                return Result.error("您已经点过赞了");
            }
            likeArray.add(commentId);
            userDao.setLikeComments(userId, likeArray.toJSONString());
        }
        return Result.data(success);
    }

    public Result cancelLikeComment(int userId, int commentId){
        boolean success = commentDao.cancelAgreeComment(commentId) > 0;
        if (success){
            JSONArray likeArray = JSON.parseArray(userDao.getLikeComments(userId));
            if (likeArray == null){
                likeArray = new JSONArray();
            }
            if (!likeArray.contains(commentId)){
                return Result.error("您还没点过赞了");
            }
            likeArray.remove(new Integer(commentId));
            userDao.setLikeComments(userId, likeArray.toJSONString());
        }
        return Result.data(success);
    }

    public Result disLikeComment(int userId, int commentId){
        boolean success = commentDao.disagreeComment(commentId) > 0;
        if (success){
            JSONArray dislikeArray = JSON.parseArray(userDao.getDislikeComments(userId));
            if (dislikeArray == null){
                dislikeArray = new JSONArray();
            }
            if (dislikeArray.contains(commentId)){
                return Result.error("您已经踩过了");
            }
            dislikeArray.add(commentId);
            userDao.setDisLikeComments(userId, dislikeArray.toJSONString());
        }
        return Result.data(success);
    }

    public Result cancelDislikeComment(int userId, int commentId){
        boolean success = commentDao.cancelDisagreeComment(commentId) > 0;
        if (success){
            JSONArray dislikeArray = JSON.parseArray(userDao.getDislikeComments(userId));
            if (dislikeArray == null){
                dislikeArray = new JSONArray();
            }
            if (!dislikeArray.contains(commentId)){
                return Result.error("未点过踩");
            }
            dislikeArray.remove(new Integer(commentId));
            userDao.setDisLikeComments(userId, dislikeArray.toJSONString());
        }
        return Result.data(success);
    }
}
