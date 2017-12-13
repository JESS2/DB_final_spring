package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.User;

@Mapper
public interface UserMapper {
   List<User> findAll();
    User selectByUserId(int user_id);
    void updateUserauth(User user);
    void insert(User user);
}