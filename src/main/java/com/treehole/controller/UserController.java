package com.treehole.controller;

import com.treehole.domain.User;
import com.treehole.service.UserService;
import com.treehole.util.FileUtil;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Result register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Result login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
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
            @RequestParam(required = false) MultipartFile image) {
        User user = new User();
        user.setId(userId);
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

}
