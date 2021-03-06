package com.hnc.sibob.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 에러 코드 관리
 */
@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {
	/**
	 * 1000 : 요청 성공
	 */
	SUCCESS(true, 1000, "요청에 성공하였습니다."),

	/**
	 * 2000 : Request 오류
	 */
	// Common
	REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
	// EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
	// INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
	// INVALID_USER_JWT(false, 2003, "권한이 없는 유저의 접근입니다."),
	CHECK_PASSWORD(false, 2004, "비밀번호가 일치하지 않습니다."),
	CHECK_USER(false, 2005, "유효하지 않은 계정입니다."),
	INVALID_URI_PATH(false, 2006, "URI 경로가 잘못되었습니다."),
	EMPTY_PARAMETER(false, 2007, "파라미터 값을 확인해주세요."),
	INVALID_VERIFY_CODE(false, 2008, "잘못된 인증코드입니다."),
	EMPTY_OR_INVALID_TOKEN(false, 2009, "발급된 토큰이 없거나 파싱 오류입니다."),
	NOT_TO_DELETE(false, 2010, "삭제할 대상이 없습니다."),
	WRONG_ACCESS(false, 2011, "잘못된 접근입니다."),

	// Common 형식 관련: 2100~
	INVALID_EMAIL(false, 2101, "올바른 이메일 형식을 입력해주세요."),
	INVALID_PASSWORD(false, 2102, "영문 대소문자, 숫자, 특수문자를 3가지 이상으로 조합하여 8자 이상 입력해 주세요."),
	INVALID_PHONE(false, 2102, "올바른 전화번호 형식을 입력해주세요."),

	// [POST] null 관련: 2200~
	POST_EMPTY(false, 2200, "필수 요소를 모두 확인해주세요."),
	POST_EMPTY_EMAIL(false, 2201, "이메일은 필수정보입니다."),
	POST_EMPTY_NAME(false, 2202, "이름은 필수정보입니다."),
	POST_EMPTY_PHONE(false, 2203, "전화번호는 필수정보입니다."),
	POST_EMPTY_PASSWORD(false, 2204, "비밀번호는 필수정보입니다."),

	USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

	// [POST] /users
	POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
	POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
	POST_USERS_EXISTS_EMAIL(false, 2017, "중복된 이메일입니다."),
	POST_USERS_EXISTS_NICKNAME(false, 2018, "중복된 닉네임입니다."),
	POST_USERS_EXISTS_PHONE_NUMBER(false, 2019, "중복된 휴대폰 번호입니다."),

	POST_USERS_INVALID_NICKNAME(false, 2020, "잘못된 닉네임입니다."),
	POST_USERS_INVALID_PASSWORD(false, 2021, "잘못된 비밀번호 형식입니다."),

	VALID_INPUT_NULL(false, 2031, "입력되지 않은 값이 있습니다."),
	VALID_INPUT_BLANK(false, 2032, "입력되지 않은 값이 있습니다."),

	INVALID_DISTRICT_CODE(false, 2040, "존재하지 않는 지역코드입니다."),
	SEARCHED_DUPLICATE_REGION(false, 2041, "해당 코드에 대해 중복된 지역이 검출되었습니다."),

	// [2400:JWT 에러]
	EMPTY_JWT(false, 2401, "JWT를 입력해주세요."),
	INVALID_JWT(false, 2402, "유효하지 않은 JWT입니다."),
	INVALID_USER_JWT(false, 2403, "권한이 없는 유저의 접근입니다."),
	EXPIRE_JWT(false, 2404, "만료된 JWT 토큰입니다."),
	UNSUPPORTED_JWT(false, 2405, "지원되지 않은 토큰입니다."),

	/**
	 * 3000 : Response 오류
	 */
	// Common
	RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

	RESPONSE_EMPTY(false, 3001, "조회할 정보가 없습니다."),

	DELETE_EMPTY(false, 3002, "삭제할 정보가 없습니다."),

	// [POST]
	FAILED_TO_LOGIN(false, 3014, "없는 아이디거나 비밀번호가 틀렸습니다."),

	// [PLACE]
	SEARCH_NOT_FOUND_PLACE(false, 3015, "조회된 장소가 없습니다"),

	SEARCH_NOT_FOUND_POST(false, 3016, "조회된 포스트가 없습니다"),

	/**
	 * 4000 : Database, Server 오류
	 */
	DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),

	SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

	SMS_ERROR(false, 4002, "SMS 인증 요청에 실패하였습니다."),

	ENCRYPTION_ERROR(false, 4011, "암호화에 실패하였습니다."),

	DECRYPTION_ERROR(false, 4012, "복호화에 실패하였습니다."),

	S3_UPLOAD_ERROR(false, 4021, "이미지 업로드에 실패하였습니다."),

	S3_FORMAT_ERROR(false, 4022, "이미지 형식이 올바르지 않습니다."),

	S3_DELETE_ERROR(false, 4023, "이미지 삭제에 실패하였습니다."),

	SEND_MAIL_ERROR(false, 4024, "이메일 전송에 실패하였습니다."),

	AES256_INVALID_SPEC(false, 4025, "AES256 초기화에 오류가 있습니다."),

	AES256_NO_SUCH_SPEC(false, 4026, "지정한 AES256 스펙을 찾을 수 없습니다.");

	private final boolean isSuccess;
	private final int code;
	private final String message;

}
