package com.example.demo.serviceImpl;

import com.example.demo.Dao.TbUserDAO;
import com.example.demo.entity.TbUser;
import com.example.demo.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    TbUserDAO userDAO;

    @CachePut(value = "user", key = "#user.id")
    @Transactional
    @Override
    public int insert(TbUser user) {
        int i=userDAO.insert(user);
        System.out.println(user.getId());
        return i;

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

    @Override
    public String uploadFile(MultipartFile zipFile) {
        String targetFilePath = "H:\\uploadFile";
        String fileName = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(targetFilePath + File.separator + fileName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
            logger.info("------>>>>>>uploaded a file successfully!<<<<<<------");
        } catch (IOException e) {
            return "false";
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return "成功";
    }
}
