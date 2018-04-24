package com.allentest.service.imp;

import com.allentest.dao.OrderDao;
import com.allentest.entity.Order;
import com.allentest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AllenWish on 2018/4/25.
 */
@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    OrderDao orderDao;

    @Override
    public void createIfNotExistsTable() {
        orderDao.createIfNotExistsTable();
    }

    @Override
    public void truncateTable() {
        orderDao.truncateTable();
    }

    @Override
    public Long save(Order model) {
        return orderDao.insert(model);
    }

    @Override
    public void delete(Long orderId) {
        orderDao.delete(orderId);
    }

    @Override
    public void dropTable() {
        orderDao.dropTable();
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }
}
