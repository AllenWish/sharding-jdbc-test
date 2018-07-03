package com.allentest.controller;

import com.allentest.entity.TOrder;
import com.allentest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by AllenWish on 2018/4/23.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/add")
    public Object add() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                TOrder TOrder = new TOrder();
                TOrder.setUserId((long) j);
                TOrder.setOrderId((long) i);
                orderService.save(TOrder);
            }
        }
        return "success";
    }

    @RequestMapping("query")
    public Object queryAll() {
        return orderService.findAll();
    }

    @GetMapping("/find/{userId}")
    public Object queryById(@PathVariable Integer userId) {
        return orderService.findById(userId);
    }
}
