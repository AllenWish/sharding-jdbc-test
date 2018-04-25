package com.allentest.service;

import com.allentest.entity.Order;

import java.util.List;


public interface OrderService{
    void createIfNotExistsTable();

    void truncateTable();

    Long save(Order model);

    void delete(Long orderId);

    void dropTable();

    List<Order> findAll();


    List<Order> findById(long userId);
}
