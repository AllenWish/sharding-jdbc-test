package com.allentest.service;

import com.allentest.entity.TOrder;

import java.util.List;


public interface OrderService{

    int save(TOrder model);

    void delete(Long orderId);


    List<TOrder> findAll();


    List<TOrder> findById(long userId);
}
