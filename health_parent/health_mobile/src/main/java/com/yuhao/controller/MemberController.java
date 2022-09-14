package com.yuhao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.yuhao.constant.MessageConstant;
import com.yuhao.constant.RedisMessageConstant;
import com.yuhao.entity.Result;
import com.yuhao.pojo.Member;
import com.yuhao.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {


    @Reference
    private MemberService memberService;


    @Autowired
    private JedisPool jedisPool;

    //使用手机号和验证码登录


    //使用手机号和验证码登录
    @RequestMapping("/login")
    public Result login(HttpServletResponse response, @RequestBody Map map){

        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        //从Redis中获取缓存的验证码
        String codeInRedis=jedisPool.getResource().get(telephone+ RedisMessageConstant.SENDTYPE_LOGIN);
        if (codeInRedis==null||!codeInRedis.equals(validateCode)){
            //验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }else{
            //验证码输入正确
            //判断当前用户是否为会员
           Member member= memberService.findByTelephone(telephone);
           if (member==null){
               //当前不是会员，自动完成注册
               member= new Member();
               member.setPhoneNumber(telephone);
               member.setRegTime(new Date());
               memberService.add(member);
           }

           //登录成功
            // 写入Coolie，跟踪用户
            Cookie cookie = new Cookie("login_member_telephone", telephone);
           //路径
           cookie.setPath("/");
           cookie.setMaxAge(60*60*24*30);//有效期30天

            response.addCookie(cookie);
            //保存会员信息到redis中
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone,60*30,json);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }
    }

}
