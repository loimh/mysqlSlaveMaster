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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SendMailUtil sendMailUtil;

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST )
    public int insertUser(@RequestBody String Json){
        TbUser jsonpObject=JSONObject.parseObject(Json, (Type) TbUser.class);
        return userService.insert(jsonpObject);
    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    @PostMapping("/deleteUser")
    public int deleteUser(@RequestParam Integer id){
        return userService.deleteByPrimaryKey(id);
    }

    @PostMapping("/getList")
    public List getList(@RequestBody String json) throws ArithmeticException{
        //给qq邮箱发送邮件事例
//        sendMailUtil.sendMail("765783376@qq.com","邮件标题","邮件内容");
//        try{
//            int i=1/0;
//        }catch (Exception e){
//            throw new APIException(3000,e.getMessage());
//        }

        TbUser jsonpObject=JSONObject.parseObject(json, (Type) TbUser.class);
        return userService.getList(jsonpObject);
//        return  new ResultVO(userService.getList(jsonpObject));
    }
}
