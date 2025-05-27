package com.laa.nolasa.laanolasa.authentication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenRepositoryTest {

    TokenRepository tokenRepository;

    @BeforeEach
    void setUp() {
        tokenRepository = new TokenRepository();
    }

    @Test
    void clearRemovesToken() {
        tokenRepository.get(() -> new CachedToken("token", Instant.now().plusMillis(5000)));
        // Should return the existing token without calling the supplier
        String token = tokenRepository.get(() -> {
            fail("Token should have been cached and not regenerated");
            return null;
        });
        assertThat(token).isEqualTo("token");

        tokenRepository.clear();

        // Should now call the supplier since the cache was cleared
        String newToken = tokenRepository.get(() -> null);
        assertThat(newToken).isNull();
    }

    @Test
    void newTokenIsGeneratedWhenCachedOneExpires() {
        CachedToken expired = new CachedToken("token", Instant.now().minusMillis(5000));
        CachedToken fresh = new CachedToken("new-token", Instant.now().plusMillis(5000));

        // Cache the expired token
        tokenRepository.get(() -> expired);

        // Now this call should detect the token is expired and call the supplier
        String token = tokenRepository.get(() -> fresh);
        assertThat(token).isEqualTo(fresh.getToken());
    }

    @Test
    void newTokenIsGeneratedWhenNothingIsCached() {
        CachedToken token = new CachedToken("token", Instant.now());
        assertThat(tokenRepository.get(() -> token)).isNotNull();
    }
}
