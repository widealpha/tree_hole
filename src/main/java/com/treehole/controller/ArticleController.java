package com.treehole.controller;

import com.treehole.domain.Article;
import com.treehole.service.ArticleService;
import com.treehole.util.FileUtil;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/allArticles")
    Result allArticles(){
        return articleService.allArticles();
    }

    @PostMapping("/article")
    Result getArticle(@RequestParam int id){
        return articleService.getArticle(id);
    }

    @PostMapping("/myArticles")
    Result getArticleByUserId(@RequestParam int userId){
        return articleService.getArticlesByUserId(userId);
    }

    @PostMapping("/recentArticles")
    Result getArticleByTime(@RequestParam int userId, @RequestParam int days){
        return articleService.getArticleByTime(days);
    }

    @PostMapping("filterArticles")
    Result getArticleByStartEnd(@RequestParam int userId, @RequestParam String startTime, @RequestParam String endTime){
        return articleService.getArticleByStartEnd(startTime, endTime);
    }

    @PostMapping("/searchArticles")
    Result searchArticle(@RequestParam String key){
        return articleService.searchArticle(key);
    }

    @PostMapping("/isAuthor")
    Result isAuthor(@RequestParam int userId, @RequestParam int id){
        return articleService.isAuthor(userId, id);
    }

    @PostMapping("/uploadImage")
    Result uploadImage(@RequestParam MultipartFile image){
        String link = FileUtil.saveImage(image, "article");
        return Result.data(link);
    }

    @PostMapping("/uploadMusic")
    Result uploadMusic(@RequestParam MultipartFile music){
        String link = FileUtil.saveMusic(music, "music");
        return Result.data(link);
    }


    @PostMapping("/addArticle")
    Result addArticle(
            @RequestParam int userId,
            @RequestParam(required = false) String title,
            @RequestParam String content,
            @RequestParam String emotion,
            @RequestParam(required = false) String images){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setImages(images);
        article.setEmotion(emotion);
        return articleService.addArticle(userId, article);
    }

    @PostMapping("updateArticle")
    Result updateArticle(
            @RequestParam int userId,
            @RequestParam int id,
            @RequestParam(required = false) String title,
            @RequestParam String content,
            @RequestParam String emotion,
            @RequestParam(required = false) String images){
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setImages(images);
        article.setEmotion(emotion);
        return articleService.updateArticle(userId, article);
    }

    @PostMapping("deleteArticle")
    Result deleteArticle(@RequestParam int userId, @RequestParam int id){
        return articleService.deleteArticle(userId, id);
    }

    @PostMapping("/likeArticle")
    Result likeArticle(
            @RequestParam int userId,
            @RequestParam int id){
        return articleService.likeArticle(userId, id);
    }

    @PostMapping("cancelLikeArticle")
    Result cancelLikeArticle(
            @RequestParam int userId,
            @RequestParam int id){
        return articleService.cancelLikeArticle(userId, id);
    }



    @PostMapping("dislikeArticle")
    Result dislikeArticle(
            @RequestParam int userId,
            @RequestParam int id){
        return articleService.disLikeArticle(userId, id);
    }

    @PostMapping("cancelDislikeArticle")
    Result cancelDislikeArticle(
            @RequestParam int userId,
            @RequestParam int id){
        return articleService.cancelDislikeArticle(userId, id);
    }

}
