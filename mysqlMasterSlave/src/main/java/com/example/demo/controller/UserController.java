package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.TbUserDAO;
import com.example.demo.entity.TbUser;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

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
    public List getList(@RequestBody String json){
        TbUser jsonpObject=JSONObject.parseObject(json, (Type) TbUser.class);
        return userService.getList(jsonpObject);
    }
}
