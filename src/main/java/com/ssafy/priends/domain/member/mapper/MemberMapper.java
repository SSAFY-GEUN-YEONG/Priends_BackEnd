package com.ssafy.priends.domain.member.mapper;

import com.ssafy.priends.domain.member.dto.MemberGetDto;
import com.ssafy.priends.domain.member.dto.MemberInfoDto;
import com.ssafy.priends.domain.member.dto.MemberLoginRequestDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	int emailCheckMember(String userEmail);

	void signUpMember(MemberDto memberDto);

	void updateMember(MemberDto memberDto);

	MemberGetDto getMember(Long memberId);

	void deleteMember(Long memberId);

	MemberInfoDto loginMember(MemberLoginRequestDto memberLoginDto);

	String getPasswordMember(Long memberId);
}
