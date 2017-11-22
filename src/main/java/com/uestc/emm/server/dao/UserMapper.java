package com.uestc.emm.server.dao;

import com.uestc.emm.server.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    User selectByEntity(User user);

    User selectByPhone(@Param("phone") String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}