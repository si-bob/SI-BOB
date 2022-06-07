package com.hnc.sibob.service.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.hnc.sibob.global.constant.BaseStatus;
import com.hnc.sibob.global.time.BaseEntity;
import com.hnc.sibob.service.member.constant.Authority;

import lombok.Builder;
import lombok.Getter;

@Getter
@DynamicInsert
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;

	private String name;

	private String nickname;

	private Authority role;

	private Character receiveYn;

	private String imageUrl;

	private BaseStatus status;

	public Member() {
	}

	@Builder
	public Member(String email, String password, String name, String nickname,
		Authority role, Character receiveYn, String imageUrl, BaseStatus status) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.role = role;
		this.receiveYn = receiveYn;
		this.imageUrl = imageUrl;
		this.status = status;
	}
}
