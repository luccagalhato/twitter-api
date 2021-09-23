package br.com.lucca.twitterapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucca.twitterapi.DTO.AccessTokenResponse;
import br.com.lucca.twitterapi.DTO.OAuthTokenResponse;
import br.com.lucca.twitterapi.DTO.UserDataResponse;
import br.com.lucca.twitterapi.mapper.AccessTokenMapper;
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

	@Autowired
	private AccessTokenMapper accessTokenMapper;

	private OkHttpClient client = new OkHttpClient().newBuilder().build();

	public OAuthTokenResponse getOAuthToken() throws IOException {
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				.url(ConstantsUtils.TWITTER_API + "/oauth/request_token?" + oAuthUtils.getOAuthQueryParameters())
				.method("POST", body).build();
		Response responseData = client.newCall(request).execute();

		if (!responseData.isSuccessful()) {
			throw new HttpStatusCodeException(HttpStatus.FORBIDDEN, responseData.peekBody(2048).string()) {
			};
		}

		return authTokenMapper.oAuthTokenMapperToResponse(responseData.peekBody(2048).string());
	}

	public AccessTokenResponse getAccessToken(String oAuthToken, String oAuthVerifier) throws IOException {
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				.url(ConstantsUtils.TWITTER_API + "/oauth/access_token?"
						+ oAuthUtils.getAccessTokenQueryParameters(oAuthToken, oAuthVerifier))
				.method("POST", body).build();
		Response responseData = client.newCall(request).execute();

		if (!responseData.isSuccessful()) {
			throw new HttpStatusCodeException(HttpStatus.FORBIDDEN, responseData.peekBody(2048).string()) {
			};
		}

		return this.accessTokenMapper.accessTokenMapperToResponse(responseData.peekBody(2048).string());
	}

	public UserDataResponse getUserData(String accessToken, String accessTokenSecret) throws IOException {
		MediaType mediaType = MediaType.parse("text/plain");
		Request request = new Request.Builder()
				.url(ConstantsUtils.TWITTER_API + "/1.1/account/verify_credentials.json?"
						+ oAuthUtils.getUserDataQueryParameters(accessToken, accessTokenSecret)).build();
		Response responseData = client.newCall(request).execute();

		if (!responseData.isSuccessful()) {
			throw new HttpStatusCodeException(HttpStatus.FORBIDDEN, responseData.peekBody(2048).string()) {
			};
		}

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(responseData.peekBody(2048).string(), UserDataResponse.class);
	}

}
