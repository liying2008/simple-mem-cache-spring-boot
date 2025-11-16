/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot;

import cc.duduhuo.simplememcache.CacheListener;
import cc.duduhuo.simplememcache.SimpleCache;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties(prefix = "simple.cache")
public class SimpleCacheProperties {

    /**
     * 缓存最大容量，0 表示不限制，默认 0
     */
    private int maxSize = SimpleCache.MAX_SIZE_DEFAULT_VALUE;

    /**
     * 默认缓存过期时间，0 表示永不过期，精度：毫秒，默认 0
     */
    @DurationUnit(ChronoUnit.MILLIS)
    private Duration defaultTtl = Duration.ofMillis(SimpleCache.DEFAULT_TTL_MILLIS_DEFAULT_VALUE);

    /**
     * 是否启用自动清理线程，默认 true
     */
    private boolean autoClean = SimpleCache.AUTO_CLEAN_DEFAULT_VALUE;

    /**
     * 自动清理间隔（仅在 autoClean=true 时生效），精度：分钟，默认 1 分钟
     */
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration cleanInterval = Duration.ofMinutes(SimpleCache.CLEAN_INTERVAL_MINUTES_DEFAULT_VALUE);

    /**
     * 缓存事件监听器（可选），需实现 CacheListener 接口。默认 null
     */
    private Class<? extends CacheListener<Object, Object>> listener = null;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Duration getDefaultTtl() {
        return defaultTtl;
    }

    public void setDefaultTtl(Duration defaultTtl) {
        this.defaultTtl = defaultTtl;
    }

    public boolean isAutoClean() {
        return autoClean;
    }

    public void setAutoClean(boolean autoClean) {
        this.autoClean = autoClean;
    }

    public Duration getCleanInterval() {
        return cleanInterval;
    }

    public void setCleanInterval(Duration cleanInterval) {
        this.cleanInterval = cleanInterval;
    }

    public Class<? extends CacheListener<Object, Object>> getListener() {
        return listener;
    }

    public void setListener(Class<? extends CacheListener<Object, Object>> listener) {
        this.listener = listener;
    }
}
