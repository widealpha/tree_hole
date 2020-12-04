package com.treehole.dao;

import com.treehole.domain.User;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Mapper
public interface UserDao {
    @Insert("INSERT INTO user_table (username, password) " +
            "VALUES(#{username}, #{password})")
    Integer addUser(String username, String password);

    @Select("SELECT id, username, nickname, motto, head_image FROM user_table WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT id, username, nickname, motto, head_image FROM user_table WHERE username LIKE #{username}")
    User getUserByName(String username);

    @Select("SELECT id, username, nickname, motto, head_image FROM user_table WHERE username LIKE '%${username}%'")
    List<User> searchUserByName(String username);

    @Update("UPDATE user_table SET nickname = #{nickname}, motto = #{motto}, head_image = #{headImage}")
    Integer updateUser(User user);

    @Select("SELECT id FROM user_table WHERE username = #{username} AND password = #{password}")
    Integer invalidatePassword(String username, String password);

    @Update("UPDATE user_table SET password = #{newPassword} WHERE id = #{id}")
    Integer changePassword(Integer id, String newPassword);

    @Delete("DELETE FROM user_table WHERE id = #{id}")
    Integer deleteUser(Integer id);

    @Select("SELECT COUNT(*) FROM admin_table WHERE user_id = #{userId}")
    Integer isAdmin(Integer userId);

    @Select("SELECT * FROM user_table")
    List<User> allUser();
}
