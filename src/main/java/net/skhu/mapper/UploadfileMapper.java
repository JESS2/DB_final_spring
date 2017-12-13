package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Uploadfile;

@Mapper
public interface UploadfileMapper {
    void insert(Uploadfile uploadfile);
    void delete(int id);
    List<Uploadfile> findByRoomId(int mentoroom_id);

}