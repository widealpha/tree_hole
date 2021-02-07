package com.treehole.service;

import com.treehole.dao.DiaryDao;
import com.treehole.domain.Diary;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    @Autowired
    DiaryDao diaryDao;

    public Result addDiary(Diary diary) {
        return Result.data(diaryDao.addDiary(diary) > 0);
    }

    public Result updateDiary(Diary diary) {
        return Result.data(diaryDao.updateDiary(diary) > 0);
    }

    public Result deleteDiary(int userId, int diaryId) {
        return Result.data(diaryDao.deleteDiary(userId, diaryId) > 0);
    }

    public Result allDiary(int userId){
        return Result.data(diaryDao.allDiary(userId));
    }

    public Result allImage(int userId){
        return Result.data(diaryDao.allImage(userId));
    }

    public Result allMusic(int userId){
        return Result.data(diaryDao.allMusic(userId));
    }

    public Result all(int userId){
        return Result.data(diaryDao.all(userId));
    }
}
