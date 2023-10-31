package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.MemberDto;

public interface MemberService {
	
	int emailCheckMember(String userEmail);
	
	void signUpMember(MemberDto memberDto);
}
