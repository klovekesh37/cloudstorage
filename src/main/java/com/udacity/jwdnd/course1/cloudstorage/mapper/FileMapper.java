package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> findAll(Long userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File findFileById(Long fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId} AND fileid = #{fileId}")
    File findFilesByUserIdAndFileId(Long userId, Long fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId} AND filename = #{fileName}")
    File findFileByUserIdAndFileName(Long userId, String fileName);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Long createFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    Integer deleteFileById(Long fileId);

}
