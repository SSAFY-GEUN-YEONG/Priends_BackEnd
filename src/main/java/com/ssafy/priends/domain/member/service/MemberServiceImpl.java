package com.ssafy.priends.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	
	// 이메일 중복 체크
	@Override
	public int emailCheckMember(String userEmail) {
		return memberMapper.emailCheckMember(userEmail);
	}

	// 회원가입 처리
	@Override
	public void signUpMember(MemberDto memberDto) {
		memberDto.setRole("USER");
		memberMapper.signUpMember(memberDto);
	}

}
