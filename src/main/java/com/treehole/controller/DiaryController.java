package com.treehole.controller;

import com.treehole.domain.Diary;
import com.treehole.service.DiaryService;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    @PostMapping("/all")
    Result all(int userId) {
        return diaryService.all(userId);
    }

    @PostMapping("/allDiary")
    Result allDiary(int userId) {
        return diaryService.allDiary(userId);
    }

    @PostMapping("allImage")
    Result allImage(int userId) {
        return diaryService.allImage(userId);
    }

    @PostMapping("allMusic")
    Result allMusic(int userId) {
        return diaryService.allImage(userId);
    }

    @PostMapping("addDiary")
    Result addDiary(int userId, String title, String content, String url) {
        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUrl(url);
        diary.setType(0);
        diary.setAuthor(userId);
        return diaryService.addDiary(diary);
    }

    @PostMapping("addImage")
    Result addImage(int userId, String title, String content, String url) {
        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUrl(url);
        diary.setType(1);
        diary.setAuthor(userId);
        return diaryService.addDiary(diary);
    }

    @PostMapping("addMusic")
    Result addMusic(int userId, String title, String content, String url) {
        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUrl(url);
        diary.setType(2);
        diary.setAuthor(userId);
        return diaryService.addDiary(diary);
    }

    @PostMapping("updateDiary")
    Result updateDiary(int userId, int diaryId, String title, String content, String url) {
        Diary diary = new Diary();
        diary.setDiaryId(diaryId);
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUrl(url);
        diary.setType(0);
        diary.setAuthor(userId);
        return diaryService.updateDiary(diary);
    }

    @PostMapping("updateImage")
    Result updateImage(int userId, int diaryId, String title, String content, String url) {
        Diary diary = new Diary();
        diary.setDiaryId(diaryId);
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUrl(url);
        diary.setType(1);
        diary.setAuthor(userId);
        return diaryService.updateDiary(diary);
    }

    @PostMapping("updateMusic")
    Result updateMusic(int userId, int diaryId, String title, String content, String url) {
        Diary diary = new Diary();
        diary.setDiaryId(diaryId);
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUrl(url);
        diary.setType(2);
        diary.setAuthor(userId);
        return diaryService.updateDiary(diary);
    }

    @PostMapping("delete")
    Result deleteDiary(int userId, int diaryId) {
        return diaryService.deleteDiary(userId, diaryId);
    }

}
