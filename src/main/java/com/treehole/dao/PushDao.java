package com.treehole.dao;

import com.treehole.domain.Push;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PushDao {
    @Select("SELECT * FROM push_table WHERE id = #{id}")
    Push getPush(int id);

    @Select("SELECT * FROM push_table WHERE type = #{type}")
    List<Push> getAllPush(int type);

    @Delete("DELETE FROM push_table WHERE id = #{id}")
    Integer deletePush(int id);

    @Update("UPDATE push_table SET title = #{title}, content = #{content}, type = #{type}, file = #{file} WHERE id = #{id}")
    Integer updatePush(Push push);

    @Insert("INSERT INTO push_table (type, author, title, content, file)" +
            " VALUES (#{type}, #{author}, #{title}, #{content}, #{file})")
    Integer addPush(Push push);
}
