package com.allentest.dao;

import com.allentest.entity.TOrder;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface OrderDao extends Mapper<TOrder>, MySqlMapper<TOrder> {

}
