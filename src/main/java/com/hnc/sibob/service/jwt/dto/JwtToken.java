package com.hnc.sibob.service.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtToken {

	private String grantType;
	private String accessToken;
	private String refreshToken;
	private Long accessTokenExpiresIn;

	public JwtToken() {
	}

	@Builder
	public JwtToken(String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
		this.grantType = grantType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}
}
