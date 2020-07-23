package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> findAll();

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User findById(Long userId);

    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    User findByUserName(String userName);

    @Insert("INSERT INTO USERS (username, password, salt, firstname, lastname) VALUES (#{userName}, #{password}, #{salt}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Long createUser(User user);

    @Delete("DELETE FROM USERS WHERE username = #{userName}")
    void deleteUserByUserName(String userName);

    @Delete("DELETE FROM USERS WHERE userid = #{userId}")
    void deleteUserById(Long userId);

    @Update("UPDATE USERS SET username = #{userName}, password = #{password}, salt = #{salt}, firstname = #{firstName}, lastname = #{lastName} WHERE userid = #{userId}")
    Integer updateUser(User user);

}
