package com.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {

    private final MemcachedClient memcachedClient;

    public void set(String key, String value, int expiration) {
        try {
            memcachedClient.set(key, expiration, value);
            log.info("Cache atualizado: key={}, expiration={}s", key, expiration);
        } catch (Exception e) {
            log.error("Erro ao definir cache: key={}, error={}", key, e.getMessage(), e);
            throw new RuntimeException("Erro ao definir cache", e);
        }
    }

    public Optional<String> get(String key) {
        try {
            return Optional.ofNullable(memcachedClient.get(key));
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            log.error("Erro ao obter cache: key={}, error={}", key, e.getMessage(), e);
            return Optional.empty();
        }
    }

    public void delete(String key) {
        try {
            memcachedClient.delete(key);
            log.info("Cache removido: key={}", key);
        } catch (Exception e) {
            log.error("Erro ao remover cache: key={}, error={}", key, e.getMessage(), e);
            throw new RuntimeException("Erro ao remover cache", e);
        }
    }
}
