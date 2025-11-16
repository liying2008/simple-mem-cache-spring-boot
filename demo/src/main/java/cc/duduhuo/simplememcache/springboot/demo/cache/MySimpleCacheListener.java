/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot.demo.cache;

import cc.duduhuo.simplememcache.CacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySimpleCacheListener implements CacheListener<Object, Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySimpleCacheListener.class);

    @Override
    public void onRemove(Object key, Object value, String reason) {
        LOGGER.info("Removed [{}] = {} because {}", key, value, reason);
    }
}
