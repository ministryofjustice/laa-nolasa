package com.laa.nolasa.laanolasa.authentication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.laa.nolasa.laanolasa.exception.AuthException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.w3c.dom.NodeList;

@ExtendWith(MockitoExtension.class)
class ClientCredentialsSoapHandlerTest {

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private SOAPMessageContext messageContext;

    @InjectMocks
    private ClientCredentialsSoapHandler handler;

    private final String jwt = "dummy.jwt.token";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(handler, "clientSecret", "my-test-secret");
    }

    @Test
    void shouldAddClientCredentialsHeaderToOutboundMessage() throws Exception {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        when(messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(true);
        when(messageContext.getMessage()).thenReturn(soapMessage);
        when(tokenRepository.get(any())).thenReturn(jwt);

        boolean result = handler.handleMessage(messageContext);

        SOAPHeader header = soapMessage.getSOAPHeader();
        NodeList nodes = header.getElementsByTagNameNS("http://justice.gov.uk/laa/infox", "ClientCredentials");
        assertThat(nodes.getLength()).isGreaterThan(0);
        assertThat(nodes.item(0).getTextContent()).startsWith("Bearer ");
        assertThat(result).isTrue();
    }

    @Test
    void shouldSkipInboundMessage() {
        when(messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(false);
        boolean result = handler.handleMessage(messageContext);
        assertThat(result).isTrue();
    }

    @Test
    void shouldThrowIfSecretMissing() throws SOAPException {
        ReflectionTestUtils.setField(handler, "clientSecret", "");

        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        when(messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(true);
        when(messageContext.getMessage()).thenReturn(soapMessage);

        assertThatThrownBy(() -> handler.handleMessage(messageContext))
                .isInstanceOf(AuthException.class)
                .hasMessage("Failed to add JWT header");
    }

    @Test
    void shouldReturnTrueOnHandleFault() {
        assertThat(handler.handleFault(messageContext)).isTrue();
    }
}
