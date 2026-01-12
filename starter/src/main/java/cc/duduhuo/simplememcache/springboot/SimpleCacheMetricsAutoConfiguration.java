/*
 * Copyright 2025-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot;

import cc.duduhuo.simplememcache.SimpleCache;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({MeterRegistry.class, SimpleCache.class})
public class SimpleCacheMetricsAutoConfiguration {

    @Bean
    public SimpleCacheMetricsBinder simpleCacheMetricsBinder(SimpleCache<?, ?> simpleCache) {
        return new SimpleCacheMetricsBinder(simpleCache);
    }
}
