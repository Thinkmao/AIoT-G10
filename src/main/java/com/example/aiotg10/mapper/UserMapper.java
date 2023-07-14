package com.example.aiotg10.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiotg10.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询
     */
    @Select("select * from user")
    List<User> getAll();
}
