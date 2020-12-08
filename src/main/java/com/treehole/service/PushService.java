package com.treehole.service;

import com.treehole.dao.PushDao;
import com.treehole.dao.UserDao;
import com.treehole.domain.Push;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushService {
    @Autowired
    private PushDao pushDao;
    @Autowired
    private UserDao userDao;

    public Result getAllChickenSouls(){
        return Result.data(pushDao.getAllPush(0));
    }

    public Result getAllMusicPush(){
        return Result.data(pushDao.getAllPush(1));
    }

    public Result deletePush(int userId, int id){
        Integer author = pushDao.getPush(id).getAuthor();
        if (userId != author && userDao.isAdmin(userId) == 0) {
            return Result.error("您无权删除");
        }
        return Result.data(pushDao.deletePush(id) > 0);
    }

    public Result addChickenSoul(Push push){
        push.setType(0);
        return Result.data(pushDao.addPush(push) > 0);
    }

    public Result addMusicPush(Push push){
        push.setType(1);
        return Result.data(pushDao.addPush(push) > 0);
    }

    public Result updatePush(int userId, Push push){
        Integer author = pushDao.getPush(push.getId()).getAuthor();
        if (userId != author && userDao.isAdmin(userId) == 0) {
            return Result.error("您无权更改");
        }
        return Result.data(pushDao.updatePush(push));
    }
}
