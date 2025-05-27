package com.laa.nolasa.laanolasa.authentication;

import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CachedToken {

    private final String token;
    private final Instant expiry;

    public boolean isExpired() {
        return Instant.now().isAfter(expiry);
    }
}

