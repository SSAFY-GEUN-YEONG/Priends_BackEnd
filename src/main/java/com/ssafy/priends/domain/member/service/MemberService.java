package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.*;
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

	void updatePasswordMember(MemberPasswordUpdateDto memberPasswordUpdateDto);
}
