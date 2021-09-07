//package com.echogateway.config;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
//
//public class CustomOauth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
//
//	 @Override
//	 protected ResponseEntity<OAuth2Exception> enhanceResponse(ResponseEntity<OAuth2Exception> result,
//				Exception authException){
//		 return new ResponseEntity<OAuth2Exception>(result.getStatusCode());
//	    }
//}