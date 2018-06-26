package com.allentest.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() throws SQLException, IOException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException, IOException {
        // 构建读写分离数据源, 读写分离数据源实现了DataSource接口, 可直接当做数据源处理. masterDataSource0, slaveDataSource00, slaveDataSource01等为使用DBCP等连接池配置的真实数据源
        /*Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("shjd-test-0", createDataSource("shjd-test"));
        dataSourceMap.put("shjd-test-1", createDataSource("shjd-test-1"));
        dataSourceMap.put("shjd-test-2", createDataSource("shjd-test-2"));


        // 构建读写分离配置
        MasterSlaveRuleConfiguration masterSlaveRuleConfig0 = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig0.setName("shjd");
        masterSlaveRuleConfig0.setMasterDataSourceName("shjd-test-0");
        //这里数据库从库配置两个，查询的时候会负载均衡去匹配一个数据去查询
        masterSlaveRuleConfig0.getSlaveDataSourceNames().add("shjd-test-1");
        masterSlaveRuleConfig0.getSlaveDataSourceNames().add("shjd-test-2");
*/
        //DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig0,new HashMap<>());
        URL resource = getClass().getResource("/application.properties");
        File file = new File(resource.getFile());
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(file);

        return dataSource;
    }

    private static DataSource createDataSource(final String dataSourceName) {
        //使用druid连接数据库
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("1234");
        return result;
    }
}