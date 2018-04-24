package com.allentest.config;

import com.google.common.base.Preconditions;
import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;
import io.shardingjdbc.core.routing.strategy.inline.InlineShardingStrategy;

import java.beans.ConstructorProperties;

/**
 * add by allenwish time:2018-4-24 18:51:39
 */
public class MyShardingStrategyConfiguration implements ShardingStrategyConfiguration {
    private final String shardingColumns;
    private final String algorithmClassName;

    public ShardingStrategy build() {
        Preconditions.checkNotNull(this.shardingColumns, "Sharding columns cannot be null.");
        Preconditions.checkNotNull(this.algorithmClassName, "Algorithm class cannot be null.");
        return new InlineShardingStrategy(this.shardingColumns, this.algorithmClassName);
    }

    @ConstructorProperties({"shardingColumns", "algorithmClassName"})
    public MyShardingStrategyConfiguration(String shardingColumns, String algorithmClassName) {
        this.shardingColumns = shardingColumns;
        System.out.println("=============================="+algorithmClassName);
        this.algorithmClassName = algorithmClassName;
    }

    public String getShardingColumns() {
        return this.shardingColumns;
    }

    public String getAlgorithmClassName() {
        return this.algorithmClassName;
    }
}
