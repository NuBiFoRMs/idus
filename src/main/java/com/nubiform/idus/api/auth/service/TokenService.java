package com.nubiform.idus.api.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {

    private final StringRedisTemplate redisTemplate;

    @Transactional
    public boolean setToken(String memberId, String token) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(memberId, token);
        return true;
    }

    @Transactional
    public boolean removeToken(String memberId) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(memberId, "");
        return true;
    }

    @Transactional
    public boolean validateToken(String memberId, String token) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String sessionToken = valueOperations.get(memberId);

        if (sessionToken == null || "".equals(sessionToken)) {
            log.debug("there is no session");
            return false;
        }

        if (!sessionToken.equals(token)) {
            log.debug("signed out session");
            return false;
        }

        return true;
    }
}
