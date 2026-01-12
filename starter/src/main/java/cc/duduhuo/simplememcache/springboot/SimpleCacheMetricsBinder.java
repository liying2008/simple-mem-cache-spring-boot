/*
 * Copyright 2025-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot;

import cc.duduhuo.simplememcache.SimpleCache;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

public class SimpleCacheMetricsBinder implements MeterBinder {
    private final SimpleCache<?, ?> simpleCache;

    public SimpleCacheMetricsBinder(SimpleCache<?, ?> simpleCache) {
        this.simpleCache = simpleCache;
    }


    @Override
    public void bindTo(MeterRegistry registry) {
        Gauge.builder("simple.cache.size", simpleCache, cache -> cache.stats().getSize())
            .baseUnit("entries")
            .description("The number of valid entries in the cache")
            .register(registry);
        Gauge.builder("simple.cache.hits", simpleCache, cache -> cache.stats().getHits())
            .baseUnit("times")
            .description("The times of cache hits")
            .register(registry);
        Gauge.builder("simple.cache.misses", simpleCache, cache -> cache.stats().getMisses())
            .baseUnit("times")
            .description("The times of cache misses")
            .register(registry);
        Gauge.builder("simple.cache.evictions", simpleCache, cache -> cache.stats().getEvictions())
            .baseUnit("times")
            .description("The times of cache evictions")
            .register(registry);
    }
}
