package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.TbUserDAO;
import com.example.demo.entity.TbUser;
import com.example.demo.service.UserService;
import com.example.demo.utils.APIException;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.SendMailUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SendMailUtil sendMailUtil;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;



    @RequestMapping(value = "/insertUser",method = RequestMethod.POST )
    public int insertUser(@RequestBody String Json){
        TbUser jsonpObject=JSONObject.parseObject(Json, (Type) TbUser.class);
        return userService.insert(jsonpObject);
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST )
    public int updateUser(@RequestBody String Json){
        TbUser jsonpObject=JSONObject.parseObject(Json, (Type) TbUser.class);
        return userService.updateByPrimaryKey(jsonpObject);
    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    @PostMapping("/deleteUser")
    public int deleteUserById(@RequestParam String id){
        return userService.deleteByPrimaryKey(id);
    }

    @PostMapping("/selectById")
    public TbUser selectUserById(@RequestParam String id){
        return userService.selectById(id);
    }

    @PostMapping("/getList")
    public List getList(@RequestBody String json) {
        //给qq邮箱发送邮件事例
//        sendMailUtil.sendMail("765783376@qq.com","邮件标题","邮件内容");
//        try{
//            int i=1/0;
//        }catch (Exception e){
//            throw new APIException(3000,e.getMessage());
//        }

        TbUser jsonpObject=JSONObject.parseObject(json, (Type) TbUser.class);
        return userService.getList(jsonpObject);
    }


    /**
     * 图片上传接口
     * @param
     * @return
     */
    @PostMapping("/upload")
    public String  Upload(@RequestParam("file") MultipartFile zipFile) {
        return userService.uploadFile(zipFile);
    }


}
