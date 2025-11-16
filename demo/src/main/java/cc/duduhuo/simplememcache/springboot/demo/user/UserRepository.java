/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.simplememcache.springboot.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    public UserEntity getUserById(Long id) {
        LOGGER.info("Get user by id from UserRepository: {}", id);
        String name = "User #" + id;
        Integer age = 18;
        return new UserEntity(id, name, age);
    }

    public List<UserEntity> getUsersByName(String name) {
        LOGGER.info("Get users by name from UserRepository: {}", name);
        return Arrays.asList(
            new UserEntity(1L, name, 18),
            new UserEntity(2L, name, 18)
        );
    }
}
