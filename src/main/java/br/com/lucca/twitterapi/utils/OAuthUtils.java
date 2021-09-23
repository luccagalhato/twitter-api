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
	            .withTokenSecret(ConstantsUtils.ACCESS_TOKEN_SECRET)
	            .withConsumerSecret(ConstantsUtils.SECRET_KEY)
	            .withParameter("oauth_callback", ConstantsUtils.CALLBACK_URL)
	            .withParameter("oauth_consumer_key", ConstantsUtils.API_KEY)
	            .withParameter("oauth_token", ConstantsUtils.ACCESS_TOKEN)
	            .withParameter("oauth_nonce", getNonce())
	            .build();
		
		oauthQuery = oauthQuery.replace("OAuth ", "");
		oauthQuery = oauthQuery.replace(", ", "&");
		oauthQuery = oauthQuery.replace("\"", "");
		
		System.out.println(oauthQuery);
		
		return oauthQuery;
	}
	
	public String getAccessTokenQueryParameters(String oAuthToken, String oAuthVerifier) {
		String oauthQuery = new OAuth1AuthorizationHeaderBuilder()
				.withMethod("POST")
	            .withURL("https://api.twitter.com/oauth/request_token")
	            .withTokenSecret(ConstantsUtils.ACCESS_TOKEN_SECRET)
	            .withConsumerSecret(ConstantsUtils.SECRET_KEY)
	            .withParameter("oauth_consumer_key", ConstantsUtils.API_KEY)
	            .withParameter("oauth_token", oAuthToken)
	            .withParameter("oauth_verifier", oAuthVerifier)
	            .withParameter("oauth_nonce", getNonce())
	            .build();
		
		oauthQuery = oauthQuery.replace("OAuth ", "");
		oauthQuery = oauthQuery.replace(", ", "&");
		oauthQuery = oauthQuery.replace("\"", "");
		
		System.out.println(oauthQuery);
		
		return oauthQuery;
	}
	
	public String getUserDataQueryParameters(String accessToken, String accessTokenSecret) {
		String oauthQuery = new OAuth1AuthorizationHeaderBuilder()
				.withMethod("GET")
	            .withURL("https://api.twitter.com/1.1/account/verify_credentials.json")
	            .withTokenSecret(accessTokenSecret)
	            .withConsumerSecret(ConstantsUtils.SECRET_KEY)
	            .withParameter("oauth_consumer_key", ConstantsUtils.API_KEY)
	            .withParameter("oauth_token", accessToken)
	            .withParameter("oauth_nonce", getNonce())
	            .build();
		
		oauthQuery = oauthQuery.replace("OAuth ", "");
		oauthQuery = oauthQuery.replace(", ", "&");
		oauthQuery = oauthQuery.replace("\"", "");
		
		System.out.println(oauthQuery);
		
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
