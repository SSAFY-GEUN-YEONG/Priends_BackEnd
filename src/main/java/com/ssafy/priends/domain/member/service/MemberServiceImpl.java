package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.MemberGetDto;
import com.ssafy.priends.domain.member.dto.MemberInfoDto;
import com.ssafy.priends.domain.member.dto.MemberLoginRequestDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import com.ssafy.priends.global.component.jwt.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional 
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

	private final RefreshRepository refreshRepository;
	
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
	public TokenMemberInfoDto loginCheckMember(MemberLoginRequestDto memberLoginRequestDto) {
		// 1. 이부분 dto 따로 또 추가해줘서 해당 이메일로 먼저 db에서 조회 때린 뒤
		// 2. passwordEncoder로 패스워드 암호화 한거 db랑 확인한 뒤 일치하면 로그인 성공하게끔 처리해줘야함
		MemberDto member = memberMapper.loginCheckMember(memberLoginRequestDto);
		if(member == null) {
			throw new RuntimeException("해당 이메일을 가진 회원이 존재하지 않습니다.");
		}
		String realPassword = member.getPassword();

		// 패스워드 디코딩 해서 비교하는 부분 추가하기

		return TokenMemberInfoDto.builder()
				.id(member.getId())
				.email(member.getEmail())
				.name(member.getName())
				.alarm(member.isAlarm())
				.role(member.getRole().toString())
				.build();
	}

	@Override
	public String getPasswordMember(Long memberId) {
		// 나중에 임시비밀번호 발급으로 수정 될 예정
		return memberMapper.getPasswordMember(memberId);
	}

	@Override
	public void logoutMember(String memberEmail) {
		try {
			refreshRepository.delete(memberEmail);
		} catch(Exception e) {
			throw new RuntimeException("이미 로그아웃 된 상태입니다.");
		}
	}

}
