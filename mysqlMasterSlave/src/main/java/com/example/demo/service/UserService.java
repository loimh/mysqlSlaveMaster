package com.example.demo.service;

import com.example.demo.entity.TbUser;

import java.util.List;

public interface UserService {
    public int insert(TbUser user);

    public int  deleteByPrimaryKey(Integer id);

    public List getList(TbUser user);
}
