package com.ssafy.priends.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	int emailCheckMember(String userEmail);
	
	void signUpMember(MemberDto memberDto);
}
