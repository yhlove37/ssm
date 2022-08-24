package com.yuhao.quaztz;

import com.yuhao.constant.RedisConstant;
import com.yuhao.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;


    public void clearImg(){
        Set<String> set =
                jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                        RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null){
            System.out.println("清理圖片");
            for (String picName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().
                        srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
            }
        }
    }
}