package com.ssafy.priends.domain.member.mapper;

import com.ssafy.priends.domain.member.dto.MemberDto;

public interface MemberMapper {
	void emailCheckMember(String userEmail);
	
	void signUpMember(MemberDto memberDto);
}
