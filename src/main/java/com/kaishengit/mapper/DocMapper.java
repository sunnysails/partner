package com.kaishengit.mapper;

import com.kaishengit.pojo.Doc;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/23.
 */
@Mapper
@Repository
public interface DocMapper {
    @Update("")
    void update(Doc doc);

    @Select("")
    Doc findById(Integer id);

    @Delete("")
    void delete(Integer id);

    @Select("")
    List<Doc> findAll();

    @Select("")
    List<Doc> findByFid(Integer fid);

    @Insert("INSERT INTO t_doc (name, size, createuser, type, filename, md5, fid, contexttype)\n" +
            "  VALUE (#{name}, #{size}, #{createuser}, #{type}, #{filename}, #{md5}, #{fid}, #{contexttype})")
    void save(Doc doc);
}
