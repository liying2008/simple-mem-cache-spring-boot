/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot.demo.user;

import cc.duduhuo.simplememcache.SimpleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final SimpleCache<Long, UserEntity> userIdCache;
    private final SimpleCache<String, List<UserEntity>> userNameCache;

    public UserService(UserRepository userRepository, SimpleCache<Long, UserEntity> userIdCache, SimpleCache<String, List<UserEntity>> userNameCache) {
        this.userRepository = userRepository;
        this.userIdCache = userIdCache;
        this.userNameCache = userNameCache;
        LOGGER.info("userIdCache instance is: {}", this.userIdCache);
        LOGGER.info("userNameCache instance is: {}", this.userNameCache);
    }

    public UserEntity getUserById(Long id) {
        return userIdCache.getOrLoad(id, userRepository::getUserById);
    }

    public List<UserEntity> getUsersByName(String name) {
        return userNameCache.getOrLoad(name, userRepository::getUsersByName);
    }
}
