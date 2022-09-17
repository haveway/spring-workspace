package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.entity.CertVo;
import com.kh.spring.member.model.vo.Member;

// Repository : 저장소
// 주로 DB와 관련된 작업(=="영속성"작업)을 처리하겠다는 뜻
@Repository
public class MemberDao {

	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}

	public int deleteMember(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.deleteMember", userId);
	}

	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		
		return sqlSession.selectOne("memberMapper.idCheck", checkId);
	}

	public void insertSecret(SqlSessionTemplate sqlSession, CertVo certVo) {
		
		sqlSession.insert("memberMapper.insertSecret", certVo);
		
	}

	public boolean validate(SqlSessionTemplate sqlSession, CertVo certVo) {
		
		CertVo result = sqlSession.selectOne("memberMapper.validate", certVo);
		if(result != null) {
			sqlSession.delete("memberMapper.remove", certVo);
		}
		return result != null;
	}

}
