package com.allentest.config;

import com.google.common.base.Preconditions;
import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.exception.ShardingException;
import io.shardingsphere.core.util.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.freemud.com
 *
 * @version V1.0
 * @Title:
 * @Package: com.freemud.core.data.source.config
 * @Descripttion:
 * @author: genyou.cui
 * @date: 2018/7/2 21:34
 * @Copyright: 2017 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目.
 */
@Configuration
@EnableConfigurationProperties({RuleConfigurationProperties.class, MasterSlaveRuleConfigurationProperties.class})
public class FMDataSourcesConfiguration implements EnvironmentAware {
    private static Logger logger = LoggerFactory.getLogger(FMDataSourcesConfiguration.class);
    @Autowired
    private RuleConfigurationProperties ruleProperties;

    @Autowired
    private MasterSlaveRuleConfigurationProperties masterSlaveProperties;

    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();


    @Bean
    public DataSource dataSource() throws SQLException {

        return null == masterSlaveProperties.getMasterDataSourceName()
                ? ShardingDataSourceFactory.createDataSource(dataSourceMap, ruleProperties.getShardingRuleConfiguration(), ruleProperties.getConfigMap(), ruleProperties.getProps())
                : MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveProperties.getMasterSlaveRuleConfiguration(), masterSlaveProperties.getConfigMap());
    }

    @Override
    public void setEnvironment(final Environment environment) {
        setDataSourceMap(environment);
    }

    @SuppressWarnings("unchecked")
    private void setDataSourceMap(final Environment environment) {
        String prefix = "sharding.jdbc.datasource.";
        String dataSources = environment.getProperty(prefix + "names");
        for (String each : dataSources.split(",")) {
            try {
                Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, prefix + each, Map.class);
                Preconditions.checkState(!dataSourceProps.isEmpty(), "Wrong datasource properties!");
                DataSource dataSource = DataSourceUtil.getDataSource(dataSourceProps.get("type").toString(), dataSourceProps);
                dataSourceMap.put(each, dataSource);
            } catch (final ReflectiveOperationException ex) {
                throw new ShardingException("Can't find datasource type!", ex);
            }
        }
    }
}
