package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.MemberGetDto;
import com.ssafy.priends.domain.member.dto.MemberInfoDto;
import com.ssafy.priends.domain.member.dto.MemberLoginRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional 
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	
	// 이메일 중복 체크
	@Override
	public int emailCheckMember(String memberEmail) {
		return memberMapper.emailCheckMember(memberEmail);
	}

	// 회원가입 처리
	@Override
	public void signUpMember(MemberDto memberDto) {
		memberDto.setStatus(false);
		memberMapper.signUpMember(memberDto);
	}

	@Override
	public void updateMember(MemberDto memberDto) {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public MemberGetDto getMember(Long memberId) {
		return memberMapper.getMember(memberId);
	}

	@Override
	public void deleteMember(Long memberId) {
		memberMapper.deleteMember(memberId);
	}

	@Override
	public MemberInfoDto loginMember(MemberLoginRequestDto memberLoginRequestDto) {
		// 1. 이부분 dto 따로 또 추가해줘서 해당 이메일로 먼저 db에서 조회 때린 뒤
		// 2. passwordEncoder로 패스워드 암호화 한거 db랑 확인한 뒤 일치하면 로그인 성공하게끔 처리해줘야함
		return memberMapper.loginMember(memberLoginRequestDto);
	}

	@Override
	public String getPasswordMember(Long memberId) {
		// 나중에 임시비밀번호 발급으로 수정 될 예정
		return memberMapper.getPasswordMember(memberId);
	}

}
