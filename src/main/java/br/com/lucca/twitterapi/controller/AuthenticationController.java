package br.com.lucca.twitterapi.controller;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucca.twitterapi.DTO.OAuthTokenResponse;
import br.com.lucca.twitterapi.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("request_token")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OAuthTokenResponse> generateToken() throws ClientProtocolException, IOException {
		return ResponseEntity.status(HttpStatus.CREATED)
		        .body(this.authenticationService.getOAuthToken());
	}

}
