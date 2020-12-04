package com.treehole.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TokenDao {
    @Select("SELECT user_id FROM token_table WHERE token = #{token} LIMIT 1")
    Integer getUserId(String token);

    @Select("SELECT COUNT(*) FROM token_table WHERE user_id = #{id}")
    Integer existToken(Integer userId);

    @Insert("INSERT INTO token_table SET user_id = #{userId}, token = #{token}")
    Integer addToken(Integer userId, String token);

    @Update("UPDATE token_table SET token = #{token} WHERE user_id = #{userId}")
    Integer updateToken(Integer userId, String token);

    @Delete("DELETE FROM token_table WHERE token = #{token}")
    Integer deleteTokenByToken(String token);

    @Delete("DELETE FROM token_table WHERE user_id = #{userId}")
    Integer deleteTokenById(Integer userId);
}
