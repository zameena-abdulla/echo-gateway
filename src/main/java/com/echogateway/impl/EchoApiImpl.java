package com.echogateway.impl;

import com.echogateway.EchoApiDelegate;
import com.echogateway.config.JwtAccessTokenCustomizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class EchoApiImpl implements EchoApiDelegate {

	private static final Logger LOG = LoggerFactory.getLogger(EchoApiImpl.class);

	@Autowired
	private RestTemplate restTemplate;	

	@Value("${echo.server.url}")
	private String echoServerUrl;

	@Override
	@PreAuthorize("#oauth2.hasScope('vz_test')")
	public ResponseEntity<String> callEcho(@RequestParam String param){
		LOG.info("Received params as {}", param);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(echoServerUrl)
				.queryParam("query", param);   
		String proxyUrl = builder.toUriString();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		LOG.info("Passing request to the echo server. Request  url : {}", proxyUrl);
		HttpEntity<String> response = null;
		String requestJson="{}";
		try {
			response = restTemplate.exchange(
					proxyUrl, 
					HttpMethod.GET, 
					entity, 
					String.class);    	
		} catch (RestClientResponseException | ResourceAccessException e) {
			LOG.info("Failed to get remote resource because: {}", e.getMessage());
		}
		return new ResponseEntity<>(null==response?requestJson:response.getBody(), HttpStatus.OK);
	}	
	
	public String getEchoServerUrl() {
		return echoServerUrl;
	}

	public void setEchoServerUrl(String echoServerUrl) {
		this.echoServerUrl = echoServerUrl;
	}


}
