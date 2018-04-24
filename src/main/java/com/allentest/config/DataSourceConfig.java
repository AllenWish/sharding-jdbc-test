package com.allentest.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        // 构建读写分离数据源, 读写分离数据源实现了DataSource接口, 可直接当做数据源处理. masterDataSource0, slaveDataSource00, slaveDataSource01等为使用DBCP等连接池配置的真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("shjd-test-0", createDataSource("shjd-test"));
        dataSourceMap.put("shjd-test-1", createDataSource("shjd-test-1"));
        dataSourceMap.put("shjd-test-2", createDataSource("shjd-test-2"));

        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("shjd-test-${0..2}.t_order_${0..1}");

        //通过ShardingSlaveDataSourceFactory继续创建ShardingDataSource

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new MyShardingStrategyConfiguration("user_id", "shjd-test-${user_id % 3}"));

        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new MyShardingStrategyConfiguration("order_id","t_order_${order_id % 2}"));

        DataSource  dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,new HashMap<>(),null);

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