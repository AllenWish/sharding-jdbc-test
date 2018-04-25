package com.allentest.dao;

import com.allentest.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    void createIfNotExistsTable();

    void truncateTable();

    Long insert(Order model);

    void delete(Long orderId);

    void dropTable();

    List<Order> findAll();

    List<Order> findById(long userId);
}
