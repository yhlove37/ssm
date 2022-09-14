package com.yuhao.controller;


import com.yuhao.constant.MessageConstant;
import com.yuhao.constant.RedisConstant;
import com.yuhao.constant.RedisMessageConstant;
import com.yuhao.entity.Result;
import com.yuhao.utils.SMSUtils;
import com.yuhao.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;


//    体检预约时发送手机验证码
    @RequestMapping("/send4Order")
    public Result Send4Order(String telephone){
//        生成4位数字验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try{
//         发送短信
            SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,code.toString());
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);

        }

        System.out.println("发送的手机验证码为："+code);
//        将生成的验证码存入缓存当中
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,5*60,code.toString());

//        验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    //手机快速登录时发送手机验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码
        try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,
                5 * 60,
                code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
