/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot;

import cc.duduhuo.simplememcache.CacheListener;
import cc.duduhuo.simplememcache.SimpleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SimpleCache.class)
@EnableConfigurationProperties(SimpleCacheProperties.class)
public class SimpleCacheAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCacheAutoConfiguration.class);

    private final SimpleCacheProperties properties;

    public SimpleCacheAutoConfiguration(SimpleCacheProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SimpleCache<?, ?> simpleCache() {
        Class<? extends CacheListener<Object, Object>> listenerClass = properties.getListener();
        CacheListener<Object, Object> listenerInstance = null;
        if (listenerClass != null) {
            try {
                listenerInstance = listenerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                LOGGER.error("Failed to create listener instance: {}", e.getMessage());
            }
        }
        return SimpleCache.builder()
            .maxSize(properties.getMaxSize())
            .defaultTtlMillis(properties.getDefaultTtl().toMillis())
            .autoClean(properties.isAutoClean())
            .cleanIntervalMinutes(properties.getCleanInterval().toMinutes())
            .listener(listenerInstance)
            .build();
    }
}
