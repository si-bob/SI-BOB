package com.hnc.sibob.service.jwt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnc.sibob.service.jwt.dto.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findBy(String key);
}
