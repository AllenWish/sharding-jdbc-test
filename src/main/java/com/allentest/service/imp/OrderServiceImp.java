package com.allentest.service.imp;

import com.allentest.dao.OrderDao;
import com.allentest.entity.TOrder;
import com.allentest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AllenWish on 2018/4/25.
 */
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public int save(TOrder model) {
        return orderDao.insert(model);
    }

    @Override
    public void delete(Long orderId) {
        orderDao.deleteByPrimaryKey(orderId);
    }

    @Override
    public List<TOrder> findAll() {
        return orderDao.selectAll();
    }

    @Override
    public List<TOrder> findById(long userId) {
        return null;
    }

}
