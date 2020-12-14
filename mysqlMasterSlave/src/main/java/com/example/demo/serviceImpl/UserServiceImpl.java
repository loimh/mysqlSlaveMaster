package com.example.demo.serviceImpl;

import com.example.demo.Dao.TbUserDAO;
import com.example.demo.entity.TbUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserDAO userDAO;

    @Transactional
    @Override
    public int insert(TbUser user) {
        return userDAO.insert(user);

    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDAO.deleteByPrimaryKey(id);
    }

    @Override
    public List getList(TbUser user) {
        return  userDAO.getList(user);
    }
}
