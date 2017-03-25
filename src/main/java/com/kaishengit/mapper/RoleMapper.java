package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/23.
 */
@Mapper
@Repository
public interface RoleMapper {
    @Select("SELECT *  FROM t_role")
    List<Role> findAll();
}