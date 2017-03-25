package com.kaishengit.mapper;

import com.kaishengit.pojo.UserLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/23.
 */
@Mapper
@Repository
public interface UserLogMapper {
    @Insert("INSERT INTO t_user_log (login_ip, user_id) VALUE (#{loginIp}, #{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void save(UserLog userLog);

    @Select("SELECT *\n" +
            "FROM t_user_log\n" +
            "WHERE user_id = #{userId}\n" +
            "LIMIT #{start}, #{length}")
    List<UserLog> findByUserIdWithPage(@Param("start") Integer start,
                                       @Param("length") Integer length, @Param("userId") Integer userId);
}
