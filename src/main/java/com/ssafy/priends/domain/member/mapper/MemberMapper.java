package com.ssafy.priends.domain.member.mapper;

import com.ssafy.priends.domain.member.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int emailCheckMember(String userEmail);

	void signUpMember(MemberDto memberDto);

	void updateMember(MemberDto memberDto);

	MemberGetDto getMember(Long memberId);

	void deleteMember(Long memberId);

	MemberDto loginCheckMember(MemberLoginRequestDto memberLoginRequestDto);

	String getPasswordMember(Long memberId);

	void updatePasswordMember(MemberPasswordUpdateDto memberPasswordUpdateDto);

	MemberDto loginIdCheckMember(Long memberId);

	MemberDto byEmailFindMember(String memberEmail);
}
