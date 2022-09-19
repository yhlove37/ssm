package com.yuhao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yuhao.constant.MessageConstant;
import com.yuhao.entity.Result;
import com.yuhao.service.MemberService;
import com.yuhao.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;


    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        Calendar calendar = Calendar.getInstance();
        //获取当前日期之前12月的日期
        calendar.add(Calendar.MONTH,-12);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH,1);
            list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));

        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("months",list);
        List<Integer> memberCount = memberService.findMemberCountByMonth(list);
        map.put("memberCount",memberCount);

        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        List<Map<String, Object>> list = setmealService.findSetmealCount();

        Map<String,Object> map = new HashMap<>();
        map.put("setmealCount",list);

        List<String> setmealNames = new ArrayList<>();
        for(Map<String,Object> m : list){
            String name = (String) m.get("name");
            setmealNames.add(name);
        }
        map.put("setmealNames",setmealNames);

        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
    }
}
