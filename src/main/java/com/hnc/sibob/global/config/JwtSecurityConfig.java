package com.hnc.sibob.global.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

import com.hnc.sibob.service.jwt.JwtFilter;
import com.hnc.sibob.service.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

// 직접 만든 JwtTokenProvider 와 JwtFilter 를 SecurityConfig 에 적용 시 사용
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void configure(HttpSecurity httpSecurity) {
		JwtFilter customFilter = new JwtFilter(jwtTokenProvider);
		httpSecurity.addFilter(customFilter);
	}
}
