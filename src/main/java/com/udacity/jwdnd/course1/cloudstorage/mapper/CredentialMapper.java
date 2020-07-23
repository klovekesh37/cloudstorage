package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> findAll(Long userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId} AND userid = #{userId}")
    Credential findCredentialByCredentialIdAndUserId(Long credentialId, Long userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Long createCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Integer deleteCredentialById(Long credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, key = #{key}, password = #{password}, userid = #{userId} WHERE credentialid = #{credentialId}")
    Integer updateCredential(Credential credential);
}
