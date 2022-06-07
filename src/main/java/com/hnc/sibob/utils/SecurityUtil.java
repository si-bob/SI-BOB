package com.hnc.sibob.utils;

import static com.hnc.sibob.global.response.BaseResponseStatus.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hnc.sibob.global.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {

	private SecurityUtil() {
	}

	// SecurityContext 에 유저 정보가 저장되는 시점
	// Request 가 들어왔을 때 JwtFilter 의 doFilter 에서 저장
	public static Long getCurrentMemberId() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication.getName() == null) {
			throw new BaseException(INVALID_USER_JWT);
		}

		return Long.parseLong(authentication.getName());
	}
}
