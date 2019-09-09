package com.allentest.config;

import io.shardingsphere.core.yaml.masterslave.YamlMasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sharding.jdbc.config.masterslave")
public class MasterSlaveRuleConfigurationProperties extends YamlMasterSlaveRuleConfiguration {
}
