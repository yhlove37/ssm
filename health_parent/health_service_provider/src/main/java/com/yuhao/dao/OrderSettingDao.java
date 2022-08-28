package com.yuhao.dao;

import com.yuhao.pojo.OrderSetting;

import java.util.Date;

public interface OrderSettingDao {
    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);
}
