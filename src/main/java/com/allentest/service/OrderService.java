package com.allentest.service;

import com.allentest.entity.Order;
import org.springframework.data.repository.CrudRepository;


public interface OrderService extends CrudRepository<Order, Long> {

}
