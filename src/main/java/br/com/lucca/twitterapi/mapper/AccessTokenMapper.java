package br.com.lucca.twitterapi.mapper;

import org.springframework.stereotype.Component;

import br.com.lucca.twitterapi.DTO.AccessTokenResponse;

@Component
public class AccessTokenMapper {
	
	public AccessTokenResponse accessTokenMapperToResponse(String data) {
		AccessTokenResponse response = new AccessTokenResponse();
		String [] responseAux = data.split("&");
		String [] responseOauthToken = responseAux[0].split("=");
		String [] responseOauthTokenSecret = responseAux[1].split("=");
		String [] responseUserId = responseAux[2].split("=");
		String [] responseScreenName = responseAux[3].split("=");
		response.setOauthToken(responseOauthToken[1]);
		response.setOauthTokenSecret(responseOauthTokenSecret[1]);
		response.setUserId(responseUserId[1]);
		response.setScreenName(responseScreenName[1]);
		
		return response;
	}

}
