package com.example.demo.serviceImpl;

import com.example.demo.Dao.TbUserDAO;
import com.example.demo.entity.TbUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserDAO userDAO;

    @CachePut(value = "user", key = "#user.id")
    @Transactional
    @Override
    public int insert(TbUser user) {
        return userDAO.insert(user);

    }

    @CacheEvict(value = {"userById"}, key = "#id")
    @Override
    public int deleteByPrimaryKey(String id) {
        Integer userId= new Integer(id);
        return userDAO.deleteByPrimaryKey(userId);
    }

    @Override
    public List getList(TbUser user) {
        return  userDAO.getList(user);
    }

    @Cacheable(value ={"userById"}, key = "#id")
    @Override
    public TbUser selectById(String id) {
        System.out.println("从数据库查");
        Integer userId= new Integer(id);
        return  userDAO.selectByPrimaryKey(userId);
    }

    @CacheEvict(value = {"userById"}, key = "#user.id")
    @Override
    public int updateByPrimaryKey(TbUser user) {
        return userDAO.updateByPrimaryKey(user);
    }


}
