package br.com.lucca.twitterapi.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataRequest {
	
	@NotNull(message = "access_token não pode ser nulo")
	@NotEmpty(message = "access_token não pode estar vazio")
	@JsonProperty("access_token")
	private String accessToken;
	
	@NotNull(message = "access_token_secret não pode ser nulo")
	@NotEmpty(message = "access_token_secret não pode estar vazio")
	@JsonProperty("access_token_secret")
	private String accessTokenSecret;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
}
