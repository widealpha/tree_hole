package com.treehole.controller;

import com.treehole.domain.Comment;
import com.treehole.service.CommentService;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("getComment")
    Result comment(@RequestParam Integer commentId){
        return commentService.getComment(commentId);
    }


    @PostMapping("articleComments")
    Result articleComments(@RequestParam Integer articleId){
        return commentService.getCommentsByArticleId(articleId);
    }

    @PostMapping("commentsOnComment")
    Result commentOnComment(@RequestParam Integer commentId){
        return commentService.getCommentsByCommentId(commentId);
    }

    @PostMapping("myComments")
    Result myComments(@RequestParam int userId){
        return commentService.myComments(userId);
    }

    @PostMapping("isAuthor")
    Result isAuthor(@RequestParam int userId, @RequestParam Integer commentId){
        return commentService.isAuthor(userId, commentId);
    }

    @PostMapping("addComment")
    Result addComment(
            @RequestParam Integer userId,
            @RequestParam Integer articleId,
            @RequestParam(required = false) Integer commentId,
            @RequestParam String content){
        Comment comment = new Comment();
        comment.setAuthor(userId);
        comment.setArticleId(articleId);
        comment.setContent(content);
        if (commentId == null){
            comment.setCommentId(0);
        } else {
            comment.setCommentId(commentId);
        }
        return commentService.addComment(comment);
    }

    @PostMapping("deleteComment")
    Result deleteComment(@RequestParam Integer userId, @RequestParam int commentId){
        return commentService.deleteComment(userId, commentId);
    }

    @PostMapping("likeComment")
    Result likeComment(@RequestParam Integer userId, @RequestParam Integer commentId){
        return commentService.likeComment(userId, commentId);
    }

    @PostMapping("dislikeComment")
    Result dislikeComment(@RequestParam Integer userId, @RequestParam Integer commentId){
        return commentService.disLikeComment(userId, commentId);
    }

    @PostMapping("cancelLikeComment")
    Result cancelLikeComment(@RequestParam Integer userId, @RequestParam Integer commentId){
        return commentService.cancelLikeComment(userId, commentId);
    }

    @PostMapping("cancelDislikeComment")
    Result cancelDislikeComment(@RequestParam Integer userId, @RequestParam Integer commentId){
        return commentService.cancelDislikeComment(userId, commentId);
    }
}
