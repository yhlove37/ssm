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

    //删除
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            checkItemService.delete(id);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //编辑
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    //    回顯
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}


