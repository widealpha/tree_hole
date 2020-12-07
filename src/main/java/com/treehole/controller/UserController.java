package com.treehole.controller;

import com.treehole.domain.User;
import com.treehole.service.UserService;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Result register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) String phone
            ) {
        return userService.register(username, password, phone);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Result login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("myPhone")
    Result phone(@RequestParam int userId){
        return userService.myPhone(userId);
    }

    @RequestMapping(value = "/logout")
    Result logout(@RequestParam int userId) {
        return userService.logout(userId);
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    Result getUserInfo(@RequestParam int userId) {
        return userService.getUserInfo(userId);
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    Result updateUserInfo(
            @RequestParam int userId,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String motto,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) MultipartFile image) {
        User user = new User();
        user.setId(userId);
        user.setPhone(phone);
        user.setNickname(nickname);
        user.setMotto(motto);
        return userService.updateUserInfo(user, image);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    Result changePassword(@RequestParam int userId,
                          @RequestParam String oldPassword,
                          @RequestParam String newPassword){
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @PostMapping("changePasswordAdmin")
    Result changePasswordAdmin(@RequestParam int userId, @RequestParam int changeUserId, @RequestParam String password){
        return userService.changePasswordAdmin(userId, changeUserId, password);
    }

    @PostMapping("/forceChangePassword")
    Result forceChangePassword(@RequestParam String username,@RequestParam String phone,@RequestParam String password){
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(phone)){
            return Result.error("信息不能为空");
        }
        return userService.forceChangePassword(username, phone, password);
    }

    @RequestMapping(value = "/isAdmin", method = RequestMethod.POST)
    Result isAdmin(@RequestParam int userId){
        return userService.isAdmin(userId);
    }

    @RequestMapping(value = "/allUser", method = RequestMethod.POST)
    Result allUser(){
        return userService.getUserList();
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    Result deleteUser(@RequestParam int userId, @RequestParam int deleteUserId){
        return userService.deleteUser(userId, deleteUserId);
    }

    @RequestMapping(value = "searchUser", method = RequestMethod.POST)
    Result searchUser(@RequestParam int userId, @RequestParam String username){
        return userService.searchUserByName(username);
    }

    @RequestMapping(value = "likeArticles", method = RequestMethod.POST)
    Result likeArticles(@RequestParam int userId){
        return userService.getLikeArticles(userId);
    }

    @RequestMapping(value = "dislikeArticles", method = RequestMethod.POST)
    Result disLikeArticles(@RequestParam int userId){
        return userService.getDislikeArticles(userId);
    }

    @RequestMapping(value = "updateLikeArticles", method = RequestMethod.POST)
    Result updateLikeArticles(@RequestParam int userId, @RequestParam String likeArticles){
        return userService.setLikeArticles(userId, likeArticles);
    }

    @RequestMapping(value = "updateDislikeArticles", method = RequestMethod.POST)
    Result updateDisLikeArticles(@RequestParam int userId, @RequestParam String dislikeArticles){
        return userService.setDislikeArticles(userId, dislikeArticles);
    }

    @RequestMapping(value = "likeComments", method = RequestMethod.POST)
    Result likeComments(@RequestParam int userId){
        return userService.getLikeComments(userId);
    }

    @RequestMapping(value = "dislikeComments", method = RequestMethod.POST)
    Result disLikeComments(@RequestParam int userId){
        return userService.getDislikeComments(userId);
    }

    @RequestMapping(value = "updateLikeComments", method = RequestMethod.POST)
    Result updateLikeComments(@RequestParam int userId, @RequestParam String likeComments){
        return userService.setLikeComments(userId, likeComments);
    }

    @RequestMapping(value = "updateDislikeComments", method = RequestMethod.POST)
    Result updateDisLikeComments(@RequestParam int userId, @RequestParam String dislikeComments){
        return userService.setDislikeComments(userId, dislikeComments);
    }
}
