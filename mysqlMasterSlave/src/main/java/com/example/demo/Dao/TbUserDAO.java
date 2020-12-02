package com.example.demo.Dao;

import com.example.demo.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TbUserDAO继承基类
 */
@Repository
@Mapper
public interface TbUserDAO  {
    public int insert(TbUser user);

    int deleteByPrimaryKey(Integer id);

    List<TbUser> getList(TbUser user);
}