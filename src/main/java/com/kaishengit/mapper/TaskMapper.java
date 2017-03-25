package com.kaishengit.mapper;

import com.kaishengit.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunny on 2017/3/23.
 */
@Mapper
@Repository
public interface TaskMapper {
    @Select("SELECT *\n" +
            "FROM t_task\n" +
            "WHERE (start <= #{start} OR end >= #{end}) AND user_id = #{userId}")
    List<Task> findStartEndWithUser(@Param("start") String start,
                                    @Param("end") String end, @Param("userId") Integer userId);
}
