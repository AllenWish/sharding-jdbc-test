package com.allentest.config;

import io.shardingsphere.core.yaml.sharding.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * All rights Reserved, Designed By www.freemud.com
 *
 * @version V1.0
 * @Title:
 * @Package: com.freemud.core.data.source.config
 * @Descripttion:
 * @author: genyou.cui
 * @date: 2018/7/2 22:18
 * @Copyright: 2017 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目.
 */
@ConfigurationProperties(prefix = "sharding.jdbc.config.sharding")
public class RuleConfigurationProperties extends YamlShardingRuleConfiguration {
}
