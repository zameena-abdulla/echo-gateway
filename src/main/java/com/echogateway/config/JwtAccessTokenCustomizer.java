package com.echogateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class JwtAccessTokenCustomizer extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

  private static final Logger LOG = LoggerFactory.getLogger(JwtAccessTokenCustomizer.class);

  @Autowired
  public JwtAccessTokenCustomizer(ObjectMapper mapper) {
    LOG.info("Initialized {}", JwtAccessTokenCustomizer.class.getSimpleName());
  }

  @Override
  public void configure(JwtAccessTokenConverter converter) {
    converter.setAccessTokenConverter(this);
    LOG.info("Configured {}", JwtAccessTokenConverter.class.getSimpleName());
  }

  /**
   * Spring oauth2 expects roles under authorities element in tokenMap, but keycloak provides it under resource_access. Hence extractAuthentication
   * method is overriden to extract roles from resource_access.
   *
   * @return OAuth2Authentication with authorities for given application
   */
  @Override
  public OAuth2Authentication extractAuthentication(Map<String, ?> tokenMap) {
    LOG.debug("Begin extractAuthentication: tokenMap = {}", tokenMap);
    OAuth2Authentication authentication = super.extractAuthentication(tokenMap);
    OAuth2Request oAuth2Request = authentication.getOAuth2Request();

    OAuth2Request request =
            new OAuth2Request(oAuth2Request.getRequestParameters(), oAuth2Request.getClientId(), null, true, oAuth2Request.getScope(),
                null, null, null, null);   

    Authentication usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), "N/A", null);
    LOG.debug("End extractAuthentication");
    return new OAuth2Authentication(request, usernamePasswordAuthentication);
  }
}
