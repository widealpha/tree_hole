package com.treehole.dao;

import com.treehole.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface UserDao {
    @Insert("INSERT INTO user_table (username, password, phone) " +
            "VALUES(#{username}, #{password}, #{phone})")
    Integer addUser(String username, String password, String phone);

    @Select("SELECT id, username, nickname, motto, head_image, like_articles, dislike_articles, like_comments, dislike_comments FROM user_table WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT phone FROM user_table WHERE id = #{id}")
    String getPhoneById(Integer id);

    @Select("SELECT id, username, nickname, motto, head_image FROM user_table WHERE username LIKE #{username}")
    User getUserByName(String username);

    @Select("SELECT id, username, nickname, motto, head_image FROM user_table WHERE username LIKE '%${username}%'")
    List<User> searchUserByName(String username);

    @Update("UPDATE user_table SET nickname = #{nickname}, motto = #{motto}, head_image = #{headImage}, phone = #{phone} WHERE id = #{id}")
    Integer updateUser(User user);

    @Select("SELECT id FROM user_table WHERE username = #{username} AND password = #{password}")
    Integer invalidatePassword(String username, String password);

    @Update("UPDATE user_table SET password = #{newPassword} WHERE id = #{id}")
    Integer changePassword(Integer id, String newPassword);

    @Update("UPDATE user_table SET password = #{newPassword} WHERE phone = #{phone} AND username = #{username}")
    Integer forceChangePassword(String username, String phone, String newPassword);

    @Delete("DELETE FROM user_table WHERE id = #{id}")
    Integer deleteUser(Integer id);

    @Select("SELECT COUNT(*) FROM admin_table WHERE user_id = #{userId}")
    Integer isAdmin(Integer userId);

    @Select("SELECT id, username, nickname, motto, head_image FROM user_table")
    List<User> allUser();

    @Select("SELECT like_articles FROM user_table WHERE id = #{userId}")
    String getLikeArticles(Integer userId);

    @Update("UPDATE user_table SET like_articles = #{likeArticles} WHERE id = #{userId}")
    Integer setLikeArticles(Integer userId, String likeArticles);

    @Select("SELECT dislike_articles FROM user_table WHERE id = #{userId}")
    String getDislikeArticles(Integer userId);

    @Update("UPDATE user_table SET dislike_articles = #{disLikeArticles} WHERE id = #{userId}")
    Integer setDisLikeArticles(Integer userId, String disLikeArticles);

    @Select("SELECT like_comments FROM user_table WHERE id = #{userId}")
    String getLikeComments(Integer userId);

    @Update("UPDATE user_table SET like_comments = #{likeComments} WHERE id = #{userId}")
    Integer setLikeComments(Integer userId, String likeComments);

    @Select("SELECT dislike_comments FROM user_table WHERE id = #{userId}")
    String getDislikeComments(Integer userId);

    @Update("UPDATE user_table SET dislike_comments = #{disLikeComments} WHERE id = #{userId}")
    Integer setDisLikeComments(Integer userId, String disLikeComments);
}
