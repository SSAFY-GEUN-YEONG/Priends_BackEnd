package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.dto.MemberGetDto;
import com.ssafy.priends.domain.member.dto.MemberInfoDto;
import com.ssafy.priends.domain.member.dto.MemberLoginRequestDto;

public interface MemberService {
	
	int emailCheckMember(String memberEmail);
	
	void signUpMember(MemberDto memberDto);

	void updateMember(MemberDto memberDto);

	MemberGetDto getMember(Long memberId);

	void deleteMember(Long memberId);

	MemberInfoDto loginMember(MemberLoginRequestDto memberLoginRequestDto);

	String getPasswordMember(Long memberId);
}
