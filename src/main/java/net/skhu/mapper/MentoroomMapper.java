package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Mentoroom;

@Mapper
public interface MentoroomMapper {
    List<Mentoroom> findAll();
    void insert(Mentoroom mentoroom);
    void updatePersoncount(int mento_id);
}