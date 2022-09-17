package com.kh.spring.member.model.service;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.entity.CertVo;
import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

// @Component == bean으로 등록시키겠다.

@Service // Component보다 더 구체화해서 Service bean으로 등록시킬 것을 명시하는 것!!
public class MemberServiceImpl implements MemberService {
	
	// private MemberDao memberDao = new MemberDao();
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession; // 기존의 myBatis sqlSession 대체
	
	
	@Override
	public Member loginMember(Member m) {
		// sqlSessionTemplate bean등록 후 Autowired했음 
		// 스프링이 사용 후 자동으로 객체를 알아서 반납시켜주기 때문에  close메소드로 자원반납할 필요가 없어졌다.
		// 한줄컷 가능!!!
		// Member loginUser = memberDao.loginMember(sqlSession, m);
		
		return memberDao.loginMember(sqlSession, m);
	}

	@Override
	public int insertMember(Member m) {
		// sqlSessionTemplate객체가 자동(commit)해준다.
		//int result = memberDao.insertMember(sqlSession, m);
		
		return memberDao.insertMember(sqlSession, m);
	}

	@Override
	public int updateMember(Member m) {
		return memberDao.updateMember(sqlSession, m);
	}

	@Override
	public int deleteMember(String userId) {
		return memberDao.deleteMember(sqlSession, userId);
	}

	@Override
	public int idCheck(String checkId) {
		System.out.println(memberDao.idCheck(sqlSession, checkId));
		
		return memberDao.idCheck(sqlSession, checkId);
	}

	@Override
	public String sendMail(String ip) {
		String secret = this.generateSecret();
		
		CertVo certVo = CertVo.builder().who(ip).secret(secret).build();
		
		memberDao.insertSecret(sqlSession, certVo);
		
		return secret;
		
	}
	
	public String generateSecret() {
		Random d = new Random();
		int n = d.nextInt(100000);
		Format f = new DecimalFormat("000000");
		String secret = f.format(n);
		
		return secret;
				
	}

	@Override
	public boolean validate(CertVo certVo) {
		
		return memberDao.validate(sqlSession, certVo);
	}
	
	
}
