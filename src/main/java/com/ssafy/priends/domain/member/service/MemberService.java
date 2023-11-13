package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.dto.MemberGetDto;
import com.ssafy.priends.domain.member.dto.MemberInfoDto;
import com.ssafy.priends.domain.member.dto.MemberLoginRequestDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;

public interface MemberService {
	
	int emailCheckMember(String memberEmail);
	
	void signUpMember(MemberDto memberDto);

	void updateMember(MemberDto memberDto);

	MemberGetDto getMember(Long memberId);

	void deleteMember(Long memberId);

	TokenMemberInfoDto loginCheckMember(MemberLoginRequestDto memberLoginRequestDto);

	String getPasswordMember(Long memberId);

	void logoutMember(String memberEmail);
}
