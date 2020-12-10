package com.treehole.controller;

import com.treehole.domain.Push;
import com.treehole.service.PushService;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/push")
@CrossOrigin(origins = "*",maxAge = 3600)
public class PushController {
    @Autowired
    PushService pushService;

    @PostMapping("pushMusic")
    Result pushMusic(
            @RequestParam int userId,
            @RequestParam String music,
            @RequestParam String title,
            @RequestParam(required = false) String content){
        Push push = new Push();
        push.setAuthor(userId);
        push.setContent(content);
        push.setTitle(title);
        push.setFile(music);
        return pushService.addMusicPush(push);
    }

    @PostMapping("pushChickenSoul")
    Result pushChickenSoul(
            @RequestParam int userId,
            @RequestParam String title,
            @RequestParam String content){
        Push push = new Push();
        push.setAuthor(userId);
        push.setContent(content);
        push.setTitle(title);
        return pushService.addChickenSoul(push);
    }

    @PostMapping("updatePush")
    Result updatePush(
            @RequestParam int userId,
            @RequestParam String title,
            @RequestParam(required = false) String content){
        Push push = new Push();
        push.setContent(content);
        push.setTitle(title);
        return pushService.updatePush(userId, push);
    }

    @PostMapping("allChickenSouls")
    Result getAllChickenSoul(){
        return pushService.getAllChickenSouls();
    }

    @PostMapping("allPushMusics")
    Result getAllMusicPush(){
        return pushService.getAllMusicPush();
    }

    @PostMapping("deletePush")
    Result deletePush(@RequestParam int userId, @RequestParam int id){
        return pushService.deletePush(userId, id);
    }

}
