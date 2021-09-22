package br.com.lucca.twitterapi.mapper;

import org.springframework.stereotype.Component;

import br.com.lucca.twitterapi.DTO.OAuthTokenResponse;

@Component
public class OAuthTokenMapper {
	
	public OAuthTokenResponse oAuthTokenMapperToResponse(String data) {
		OAuthTokenResponse response = new OAuthTokenResponse();
		String [] responseAux = data.split("&");
		String [] responseOauthToken = responseAux[0].split("=");
		String [] responseOauthTokenSecret = responseAux[1].split("=");
		response.setOauthToken(responseOauthToken[1]);
		response.setOauthTokenSecret(responseOauthTokenSecret[1]);
		
		return response;
	}

}
