package com.yuhao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yuhao.constant.MessageConstant;
import com.yuhao.entity.PageResult;
import com.yuhao.entity.QueryPageBean;
import com.yuhao.entity.Result;
import com.yuhao.pojo.CheckItem;
import com.yuhao.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;


    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
//        這裏使用陳查看CheckItem接受

        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkItemService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }


}



