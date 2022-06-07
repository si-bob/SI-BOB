package com.hnc.sibob.service.jwt;

import static com.hnc.sibob.global.response.BaseResponseStatus.*;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hnc.sibob.global.config.ConfigProperties;
import com.hnc.sibob.global.exception.BaseException;
import com.hnc.sibob.service.jwt.dto.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	private final ConfigProperties properties;
	private final Key key;

	public JwtTokenProvider(ConfigProperties properties) {
		this.properties = properties;
		byte[] keyBytes = Decoders.BASE64.decode(properties.getSecret());
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	private static final String AUTHORIZED_KEY = "auth";
	private static final String BEARER_TYPE = "bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;    // 30분
	private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일

	public JwtToken getTokenDTO(Authentication authentication) {
		// 권한 가져오기
		String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		// Access Token 생성
		String accessToken = Jwts.builder()
			.setSubject(authentication.getName())    // payload "sub": "name"
			.claim(AUTHORIZED_KEY, authorities)        // payload "auth": "ROLE_USER"
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
			.signWith(SignatureAlgorithm.HS512, key)
			.compact();

		// Refresh Token 생성
		String refreshToken = Jwts.builder()
			.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
			.signWith(SignatureAlgorithm.HS512, key)
			.compact();

		return JwtToken.builder()
			.grantType(BEARER_TYPE)
			.accessToken(accessToken)
			.accessTokenExpiresIn(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME).getTime())
			.refreshToken(refreshToken)
			.build();
	}

	public Authentication getAuthentication(String accessToken) {

		// 토큰 복호화
		Claims claims = parseClaims(accessToken);

		if (claims.get(AUTHORIZED_KEY) == null) {
			throw new BaseException(INVALID_JWT);
		}

		// claims로부터 권한 정보 가져오기
		/**
		 * ? : 와일드카드, 지정되지 않은 객체 받을 수 있음
		 * extends E : 지정되지 않은 객체인 ?가 E를 상속받았다는 의미
		 */
		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORIZED_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		// userDetails 객체 생성하여 Authentication 리턴
		UserDetails principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}

	public boolean validToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
			throw new BaseException(INVALID_JWT);
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
			throw new BaseException(EXPIRE_JWT);
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
			throw new BaseException(UNSUPPORTED_JWT);
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
			throw new BaseException(INVALID_JWT);
		}
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();    // TODO: means ?????
		}
	}
}
