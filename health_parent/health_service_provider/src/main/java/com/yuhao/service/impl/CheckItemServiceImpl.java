package com.yuhao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yuhao.dao.CheckItemDao;
import com.yuhao.entity.PageResult;
import com.yuhao.pojo.CheckItem;
import com.yuhao.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
          PageHelper.startPage(currentPage,pageSize);
          Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
          return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) {
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
    }

    }

