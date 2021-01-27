package com.example.demo.service;

import com.example.demo.entity.TbUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public int insert(TbUser user);

    public int  deleteByPrimaryKey(String id);

    public List getList(TbUser user);

    public TbUser selectById(String id);

    public int updateByPrimaryKey(TbUser user);

    public String uploadFile(MultipartFile zipFile);
}
