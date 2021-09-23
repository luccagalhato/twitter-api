package br.com.lucca.twitterapi.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucca.twitterapi.DTO.AccessTokenRequest;
import br.com.lucca.twitterapi.DTO.AccessTokenResponse;
import br.com.lucca.twitterapi.DTO.OAuthTokenResponse;
import br.com.lucca.twitterapi.DTO.UserDataRequest;
import br.com.lucca.twitterapi.DTO.UserDataResponse;
import br.com.lucca.twitterapi.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("request_token")
	public ResponseEntity<OAuthTokenResponse> generateOAuthToken() throws ClientProtocolException, IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService.getOAuthToken());
	}

	@PostMapping("access_token")
	public ResponseEntity<AccessTokenResponse> generateAccessToken(@RequestBody AccessTokenRequest accessTokenRequest)
			throws ClientProtocolException, IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService
				.getAccessToken(accessTokenRequest.getOauthToken(), accessTokenRequest.getOauthVerifier()));
	}

	@PostMapping("signin")
	public ResponseEntity<UserDataResponse> getUser(@RequestBody UserDataRequest userDataRequest)
			throws ClientProtocolException, IOException {
		return ResponseEntity.status(HttpStatus.OK).body(this.authenticationService
				.getUserData(userDataRequest.getAccessToken(), userDataRequest.getAccessTokenSecret()));
	}

}
