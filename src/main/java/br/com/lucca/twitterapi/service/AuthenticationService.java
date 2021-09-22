package br.com.lucca.twitterapi.service;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucca.twitterapi.DTO.OAuthTokenResponse;
import br.com.lucca.twitterapi.mapper.OAuthTokenMapper;
import br.com.lucca.twitterapi.utils.ConstantsUtils;
import br.com.lucca.twitterapi.utils.OAuthUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



@Service
public class AuthenticationService {
	
	@Autowired
	private OAuthUtils oAuthUtils;
	
	@Autowired
	private OAuthTokenMapper authTokenMapper;
	
	private OkHttpClient client = new OkHttpClient().newBuilder().build();

	public OAuthTokenResponse getOAuthToken() throws IOException {
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
		  .url(ConstantsUtils.TWITTER_API + "/oauth/request_token?" + oAuthUtils.getOAuthQueryParameters())
		  .method("POST", body)
		  .build();
		Response responseData = client.newCall(request).execute();
		
		return authTokenMapper.oAuthTokenMapperToResponse(responseData.peekBody(2048).string());
	}

}
