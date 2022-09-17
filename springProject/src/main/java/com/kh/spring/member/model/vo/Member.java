package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok(롬복)
 * - 자동 코드 생성 라이브러리
 * - 반복되는 getter, setter, toString등의 메소드 작성코드를 줄여주는 코드 다이어트 라이브러리
 * 
 * Lombok 설치방법
 * 1. 라이브러리 다운 후 적용(Maven pom.xml)
 * 2. 다운로드 된 jar파일을 찾아서 설치(작업할 IDE를 선택)
 * 3. IDE재실행
 * 
 * Lombok 사용 시 주의사항
 * - uName, bTitle과 같은 앞글자가 외자인 필드명은 만들지 말것!!
 * 	  필드명 작성시 적어도 소문자 두글자 이상으로 시작할것!!
 * 
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Member {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	
}
