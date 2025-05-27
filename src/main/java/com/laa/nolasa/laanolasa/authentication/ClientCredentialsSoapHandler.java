package com.laa.nolasa.laanolasa.authentication;

import static io.jsonwebtoken.Jwts.SIG.HS256;

import com.laa.nolasa.laanolasa.exception.AuthException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientCredentialsSoapHandler implements SOAPHandler<SOAPMessageContext> {

    @Value("${client.infox-client-secret}")
    private String clientSecret;

    @Autowired
    private TokenRepository tokenRepository;

    private static final String ISSUER = "nolasa-dev";
    private static final String AUTH_HEADER_NAME = "ClientCredentials";
    private static final long TOKEN_LIFETIME_MILLIS = Duration.ofMinutes(5).toMillis();
    private static final String AUTH_HEADER_NAMESPACE_URI = "http://justice.gov.uk/laa/infox";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (Boolean.TRUE.equals(isOutbound)) {
            try {
                SOAPMessage soapMessage = context.getMessage();
                SOAPHeader header = soapMessage.getSOAPHeader();
                if (header == null) {
                    header = soapMessage.getSOAPPart().getEnvelope().addHeader();
                }
                setClientCredentials(header);
                soapMessage.saveChanges();
            } catch (Exception e) {
                throw new AuthException("Failed to add JWT header", e);
            }
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    private void setClientCredentials(SOAPHeader header) throws SOAPException {
        if (clientSecret == null || clientSecret.isEmpty()) {
            throw new AuthException("API key is missing or invalid");
        }
        QName qname = new QName(AUTH_HEADER_NAMESPACE_URI, AUTH_HEADER_NAME);
        SOAPHeaderElement jwtHeader = header.addHeaderElement(qname);

        String token = getToken();
        jwtHeader.addTextNode("Bearer " + token);
    }

    private String getToken() {
        return tokenRepository.get(() -> {
            Instant now = Instant.now();
            Instant expiry = now.plusMillis(TOKEN_LIFETIME_MILLIS - 5000); //5s buffer
            String jwt = generateJwt(clientSecret, now, expiry);
            return new CachedToken(jwt, expiry);
        });
    }

    private String generateJwt(String clientSecret, Instant now, Instant expiry) {
        return Jwts.builder()
                .issuer(ISSUER)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(clientSecret)), HS256)
                .compact();
    }
}
