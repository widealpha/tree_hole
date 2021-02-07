package com.treehole.dao;

import com.treehole.domain.Diary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiaryDao {
    @Select("SELECT * FROM diary_table WHERE author = #{author}")
    List<Diary> all(int author);

    @Select("SELECT * FROM diary_table WHERE author = #{author} AND type = 0")
    List<Diary> allDiary(int author);

    @Select("SELECT * FROM diary_table WHERE author = #{author} AND type = 1")
    List<Diary> allImage(int author);

    @Select("SELECT * FROM diary_table WHERE author = #{author} AND type = 2")
    List<Diary> allMusic(int author);

    @Insert("INSERT INTO diary_table (title, author, type, url, content) " +
            "VALUES (#{title}, #{author}, #{type}, #{url}, #{content})")
    Integer addDiary(Diary diary);

    @Delete("DELETE FROM diary_table WHERE diary_id = #{diaryId} AND author = #{userId}")
    Integer deleteDiary(int userId, int diaryId);

    @Update("UPDATE diary_table SET title = #{title}, type = #{type}, url = #{url}, content = #{content}" +
            " WHERE diary_id = #{diaryId} AND author = #{author}")
    Integer updateDiary(Diary diary);
}
