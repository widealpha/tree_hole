package com.treehole.service;

import com.treehole.dao.TokenDao;
import com.treehole.dao.UserDao;
import com.treehole.domain.User;
import com.treehole.util.FileUtil;
import com.treehole.util.Result;
import com.treehole.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    TokenDao tokenDao;

    public Result register(String username, String password, String phone) {
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return Result.error("用户名密码不能为空");
        }
        if (userDao.getUserByName(username) != null){
            return Result.error(-2, "用户名已经存在");
        }
        int id = userDao.addUser(username, password, phone);
        if (id > 0) {
            return Result.data("");
        }
        return Result.error(-3, "注册失败");
    }

    public Result login(String username, String password) {
        Integer id = userDao.invalidatePassword(username, password);
        if (id != null && id > 0) {
            String token = TokenUtil.getToken(id);
            if (tokenDao.existToken(id) > 0) {
                tokenDao.updateToken(id, token);
            } else {
                tokenDao.addToken(id, token);
            }
            return Result.data(token);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    public Result changePassword(int id, String oldPassword, String newPassword) {
        String username = userDao.getUserById(id).getUsername();
        if (!(userDao.invalidatePassword(username, oldPassword) > 0)) {
            return Result.error("用户名密码错误");
        }
        if (!StringUtils.hasLength(newPassword)) {
            return Result.error("新密码不能为空");
        }
        if (userDao.changePassword(id, newPassword) > 0) {
            return login(username, newPassword);
        } else {
            return Result.success("密码更改失败");
        }

    }

    public Result forceChangePassword(String username, String phone, String newPassword){
        if (!StringUtils.hasLength(newPassword)){
            return Result.error("新密码不能为空");
        }
        boolean success = userDao.forceChangePassword(username, phone, newPassword) > 0;
        if (success){
            return Result.data(true);
        } else {
            return Result.success("信息不匹配");
        }
    }

    public Result logout(int id) {
        if (tokenDao.deleteTokenById(id) > 0) {
            return Result.success("退出成功");
        } else {
            return Result.error("账户未登录");
        }
    }

    public Result getUserInfo(int id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.data(user);
    }

    public Result myPhone(int id){
        return Result.data(userDao.getPhoneById(id));
    }

    public Result updateUserInfo(User user, MultipartFile image) {
        User origin = userDao.getUserById(user.getId());
        if (image == null){
            user.setHeadImage(origin.getHeadImage());
        } else {
            String path = FileUtil.saveImage(image, "treehole");
            user.setHeadImage(path);
        }
        if (user.getNickname() == null){
            user.setNickname(origin.getNickname());
        }
        if (user.getPhone() == null){
            user.setPhone(origin.getPhone());
        }
        if (user.getMotto() == null){
            user.setMotto(origin.getMotto());
        }
        if (userDao.updateUser(user) > 0) {
            return getUserInfo(user.getId());
        } else {
            return Result.error("更新失败");
        }
    }

    public Result isAdmin(int userId) {
        return Result.data(Boolean.valueOf(userDao.isAdmin(userId) > 0).toString());
    }

    public Result deleteUser(int userId, int deleteUserId) {
        if (!(userDao.isAdmin(userId) > 0)) {
            return Result.error("您不是管理员");
        }
        if (userId == deleteUserId){
            return Result.error(-2, "不能删除自己");
        }
        if (userDao.deleteUser(deleteUserId) > 0) {
            return Result.data("");
        } else {
            return Result.success("-3, 用户不存在");
        }
    }

    public Result getUserList() {
        return Result.data(userDao.allUser());
    }

    public Result searchUserByName(String username){
        return Result.data(userDao.searchUserByName(username));
    }

    public Result getLikeArticles(Integer userId){
        return Result.data(userDao.getLikeArticles(userId));
    }

    public Result setLikeArticles(Integer userId, String likeList){
        int id =  userDao.setLikeArticles(userId, likeList);
        if (id > 0){
            return getLikeArticles(userId);
        } else {
            return Result.error("修改失败");
        }
    }

    public Result getDislikeArticles(Integer userId){
        return Result.data(userDao.getDislikeArticles(userId));
    }

    public Result setDislikeArticles(Integer userId, String dislikeList){
        int id =  userDao.setLikeArticles(userId, dislikeList);
        if (id > 0){
            return getDislikeArticles(userId);
        } else {
            return Result.error("修改失败");
        }
    }

    public Result getLikeComments(Integer userId){
        return Result.data(userDao.getLikeComments(userId));
    }

    public Result setLikeComments(Integer userId, String likeList){
        int id =  userDao.setLikeComments(userId, likeList);
        if (id > 0){
            return getLikeComments(userId);
        } else {
            return Result.error("修改失败");
        }
    }

    public Result getDislikeComments(Integer userId){
        return Result.data(userDao.getDislikeComments(userId));
    }

    public Result setDislikeComments(Integer userId, String dislikeList){
        int id =  userDao.setDisLikeComments(userId, dislikeList);
        if (id > 0){
            return getDislikeComments(userId);
        } else {
            return Result.error("修改失败");
        }
    }
}
