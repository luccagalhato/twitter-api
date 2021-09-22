package br.com.lucca.twitterapi.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import br.com.lucca.twitterapi.service.OAuth1AuthorizationHeaderBuilder;

@Component
public class OAuthUtils {
	
	private static final int NONCE_LENGTH = 16;
	private static final String ALPHA_NUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String getOAuthQueryParameters() {
		String oauthQuery = new OAuth1AuthorizationHeaderBuilder()
	            .withMethod("POST")
	            .withURL("https://api.twitter.com/oauth/request_token")
	            .withTokenSecret("pYMDi2gZJvLtkgqIpUFKBUmUoD0IKacz3wlW1TXCQVzAr")
	            .withConsumerSecret("cD7LP3jrZ6PV26yaxG5NSJs18V0LrFpMqYI8Fotl8Xg8640kO1")
	            .withParameter("oauth_callback", "http://lucca-challenge-ibm-app.s3-website.us-east-2.amazonaws.com/callback")
	            .withParameter("oauth_consumer_key", "yPJT22WSBuND8YTAZq0vAa5RU")
	            .withParameter("oauth_token", "1438917623203483648-618OECKRGuhkU0fpQNpCR4lHebnoVX")
	            .withParameter("oauth_nonce", getNonce())
	            .build();
		
		oauthQuery = oauthQuery.replace("OAuth ", "");
		oauthQuery = oauthQuery.replace(", ", "&");
		oauthQuery = oauthQuery.replace("\"", "");
		
		return oauthQuery;
	}
	
	private static String getNonce() {
	    SecureRandom rnd = new SecureRandom();
	    StringBuilder sb = new StringBuilder(NONCE_LENGTH);
	    for (int i = 0; i < NONCE_LENGTH; i++) {
	      sb.append(ALPHA_NUMERIC_CHARS.charAt(rnd.nextInt(ALPHA_NUMERIC_CHARS.length())));
	    }
	    return sb.toString();
	  }	

}
