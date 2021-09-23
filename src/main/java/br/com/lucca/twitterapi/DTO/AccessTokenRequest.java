package br.com.lucca.twitterapi.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessTokenRequest {
	
	@NotNull(message = "oauth_token não pode ser nulo")
	@NotEmpty(message = "oauth_token não pode estar vazio")
	@JsonProperty("oauth_token")
	private String oauthToken;
	
	@NotNull(message = "oauth_verifier não pode ser nulo")
	@NotEmpty(message = "oauth_verifier não pode estar vazio")
	@JsonProperty("oauth_verifier")
	private String oauthVerifier;
	

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}


	public String getOauthVerifier() {
		return oauthVerifier;
	}

	public void setOauthVerifier(String oauthVerifier) {
		this.oauthVerifier = oauthVerifier;
	}
	
}
