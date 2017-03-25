package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/23.
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM t_user WHERE user_name = #{userName}")
    User findByUserName(String userName);

    @Select("SELECT * FROM t_user")
    List<User> findAll();

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User findById(Integer id);

    @Insert("INSERT INTO t_user (user_name, password, real_name, weixin, role_id)\n" +
            "VALUES (userName, passWord, realName, weiXin, roleId)")
    @Options(useGeneratedKeys = true)
    void save(User user);

    @Update("UPDATE t_user\n" +
            "SET user_name = #{userName}, password = #{password}, real_name = #{realName}, weixin = #{weiXin},\n" +
            "  createtime  = #{createtime}, role_id = #{roleId}, enable = #{enable}\n" +
            "WHERE id = #{id}")
    void update(User user);

    @Select("SELECT count(*) FROM t_user")
    Long count();

    @Select("SELECT *\n" +
            "FROM t_user\n" +
            "WHERE user_name LIKE #{name} OR real_name LIKE #{name}\n" +
            "LIMIT #{start}, #{length}")
    List<User> findPageUserOrRealName(@Param("start") Integer start,
                                      @Param("length") Integer length, @Param("name") String name);

    @Select("SELECT\n" +
            "  tr.id        AS id,\n" +
            "  tr.role_name AS role_name\n" +
            "FROM t_user tu LEFT JOIN t_role tr ON tu.role_id = tr.id\n" +
            "WHERE tu.id = #{id}")
    Role findRoleByUserId(Integer id);

    @Select("SELECT *\n" +
            "FROM t_user\n" +
            "LIMIT #{start}, #{length}")
    List<User> findPage(@Param("start") Integer start, @Param("length") Integer length);
}