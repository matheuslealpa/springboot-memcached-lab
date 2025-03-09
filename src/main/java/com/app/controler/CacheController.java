package com.app.controller;

import com.app.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
@RequiredArgsConstructor
@Slf4j
public class CacheController {

    private final CacheService cacheService;

    @PostMapping
    public ResponseEntity<Void> setCache(@RequestParam String key,
                                           @RequestParam String value,
                                           @RequestParam(defaultValue = "300") int expiration) {
        cacheService.set(key, value, expiration);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getCache(@PathVariable String key) {
        return cacheService.get(key)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deleteCache(@PathVariable String key) {
        cacheService.delete(key);
        return ResponseEntity.noContent().build();
    }

}
