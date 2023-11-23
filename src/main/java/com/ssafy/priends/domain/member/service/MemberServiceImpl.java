package com.ssafy.priends.domain.member.service;

import com.ssafy.priends.domain.member.dto.*;
import com.ssafy.priends.domain.member.dto.enums.MemberRole;
import com.ssafy.priends.domain.member.exception.MemberError;
import com.ssafy.priends.domain.member.exception.MemberException;
import com.ssafy.priends.global.common.dto.MailCodeDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import com.ssafy.priends.global.component.jwt.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.priends.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional 
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

	private final PasswordEncoder passwordEncoder;

	private final RefreshRepository refreshRepository;
	
	// 이메일 중복 체크
	@Override
	public int emailCheckMember(String memberEmail) {
		int emailCheck = memberMapper.emailCheckMember(memberEmail);
		if(emailCheck == 1) {
			throw new MemberException(MemberError.EXIST_MEMBER_EMAIL);
		}
		return emailCheck;
	}

	// 회원가입 처리
	@Override
	public void signUpMember(MemberDto memberDto) {
		memberDto.setStatus(false);
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));	// 패스워드 암호화 작업
		memberDto.setRole(MemberRole.USER);
		// 회원가입 시 프로필 이미지 default값으로 설정
		memberDto.setImage("https://mblogthumb-phinf.pstatic.net/MjAxNzAxMDdfNTcg/MDAxNDgzNzkxMzc3NjQ1.BAuEhaa3Ffc-J5Y0GP_cSZsBrQYFITqU_f8ZkMWt2vUg.qEjN-iuo6pJR4tTz6BfeAu5X7j6e2Qn8BRugUUTZgxsg.JPEG.gkfngkfn414/%EA%B7%80%EC%97%AC%EC%9A%B4_%EB%B3%B4%EB%85%B8%EB%B3%B4%EB%85%B8_%EC%BA%90%EB%A6%AD%ED%84%B0_%EA%B7%B8%EB%A6%AC%EA%B8%B0_%EA%B0%95%EC%A2%8C7.jpg?type=w420");
		memberMapper.signUpMember(memberDto);
	}

	@Override
	public void updateMember(MemberDto memberDto) {
		MemberGetDto memberGetDto = memberMapper.getMember(memberDto.getId());
		if(memberGetDto == null) {
			throw new MemberException(MemberError.NOT_EXIST_MEMBER);
		}

		memberMapper.updateMember(memberDto);
	}

	@Override
	public MemberGetDto getMember(Long memberId) {
		MemberGetDto memberGetDto = memberMapper.getMember(memberId);
		if(memberGetDto == null) {
			throw new MemberException(MemberError.NOT_EXIST_MEMBER);
		}
		return memberGetDto;
	}

	@Override
	public void deleteMember(Long memberId) {
		try {
			memberMapper.deleteMember(memberId);
		} catch (Exception e) {
			throw new MemberException(MemberError.NOT_EXIST_MEMBER);
		}
	}

	@Override
	public TokenMemberInfoDto loginCheckMember(MemberLoginRequestDto memberLoginRequestDto) {
		// 1. 이부분 dto 따로 또 추가해줘서 해당 이메일로 먼저 db에서 조회 때린 뒤
		// 2. passwordEncoder로 패스워드 암호화 한거 db랑 확인한 뒤 일치하면 로그인 성공하게끔 처리해줘야함
		MemberDto member = memberMapper.loginCheckMember(memberLoginRequestDto);
		if(member == null) {
			throw new MemberException(MemberError.NOT_FOUND_MEMBER);
		}
		String realPassword = member.getPassword();

		// 패스워드 디코딩 해서 비교하는 부분 추가하기
		if(!passwordEncoder.matches(memberLoginRequestDto.getPassword(), realPassword)) {
			throw new MemberException(MemberError.NOT_MATCH_PASSWORD);
		}

		return TokenMemberInfoDto.builder()
				.id(member.getId())
				.email(member.getEmail())
				.nickname(member.getNickname())
				.image(member.getImage())
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
			throw new MemberException(MemberError.ALREADY_MEMBER_LOGOUT);
		}
	}

	@Override
	public void updatePasswordMember(MemberPasswordUpdateDto memberPasswordUpdateDto) {
		MemberDto member = memberMapper.loginIdCheckMember(memberPasswordUpdateDto.getId());

		if(member == null) {
			throw new MemberException(MemberError.NOT_FOUND_MEMBER);
		}

		String realPassword = member.getPassword();

		if(!passwordEncoder.matches(memberPasswordUpdateDto.getNowPassword(), realPassword)) {
			throw new MemberException(MemberError.NOT_MATCH_PASSWORD);
		}

		if(passwordEncoder.matches(memberPasswordUpdateDto.getChangePassword(), realPassword)) {
			throw new MemberException(MemberError.NOW_CHANGE_MATCH_PASSWORD);
		}

		memberPasswordUpdateDto.setChangePassword(passwordEncoder.encode(memberPasswordUpdateDto.getChangePassword()));
		memberMapper.updatePasswordMember(memberPasswordUpdateDto);
	}

	// 임시 비밀번호 발급 (해당 이메일로 임시 비밀번호 발급)
	@Override
	public void sendTempPassword(String memberEmail, MailCodeDto mailCodeDto) {
		MemberDto member = memberMapper.byEmailFindMember(memberEmail);

		if(member == null) {
			throw new MemberException(MemberError.NOT_FOUND_MEMBER);
		}

		MemberPasswordUpdateDto memberPasswordUpdateDto = new MemberPasswordUpdateDto();
		memberPasswordUpdateDto.setId(member.getId());
		memberPasswordUpdateDto.setChangePassword(passwordEncoder.encode(mailCodeDto.getEmailCode()));
		memberMapper.updatePasswordMember(memberPasswordUpdateDto);
	}

}
