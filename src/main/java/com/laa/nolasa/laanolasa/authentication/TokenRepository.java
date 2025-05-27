package com.laa.nolasa.laanolasa.authentication;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class TokenRepository {

    private CachedToken cachedToken;

    public synchronized String get(Supplier<CachedToken> tokenSupplier) {
        if (cachedToken == null || cachedToken.isExpired()) {
            cachedToken = tokenSupplier.get();
        }
        return cachedToken != null ? cachedToken.getToken() : null;
    }

    public synchronized void clear() {
        cachedToken = null;
    }

}
