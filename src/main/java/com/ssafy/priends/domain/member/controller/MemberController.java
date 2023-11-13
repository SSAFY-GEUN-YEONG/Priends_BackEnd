package com.ssafy.priends.domain.member.controller;

import com.ssafy.priends.domain.member.dto.*;
import com.ssafy.priends.global.common.dto.MailCodeDto;
import com.ssafy.priends.global.common.dto.Message;
import com.ssafy.priends.global.component.jwt.dto.TokenDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import com.ssafy.priends.global.component.jwt.service.JwtService;
import com.ssafy.priends.global.infra.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ssafy.priends.domain.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {	
	
	private final MemberService memberService;

	private final EmailService emailService;

	private final JwtService jwtService;

	@GetMapping("/{email}/check")
	public ResponseEntity<Message<Void>> emailCheckMember(@PathVariable("email") String memberEmail) {
		int emailCheck = memberService.emailCheckMember(memberEmail);
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping("/signup")
	public ResponseEntity<Message<Void>> signUpMember(@RequestBody MemberDto memberDto) {
		memberService.signUpMember(memberDto);
		return ResponseEntity.ok().body(Message.success());
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> updateMember(@RequestBody MemberDto memberDto) {
		memberService.updateMember(memberDto);
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/get")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<MemberGetDto>> getMember(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		MemberGetDto memberGetDto = memberService.getMember(memberLoginActiveDto.getId());
		return ResponseEntity.ok().body(Message.success(memberGetDto));
	}

	@PutMapping("/delete")
	public ResponseEntity<Message<Void>> deleteMember(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		memberService.deleteMember(memberLoginActiveDto.getId());
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping("/login")
	public ResponseEntity<Message<MemberLoginResponseDto>> loginMember(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
//		MemberInfoDto memberInfoDto = memberService.loginMember(memberLoginRequestDto);
//		httpSession.setAttribute("loginMember", memberLoginRequestDto);
		// 로그인 이메일 정보를 쿠키에 저장 (로그인 하기 전 이메일 저장)
//		Cookie loginEmailCookie = new Cookie("savedEmail", memberLoginRequestDto.getEmail());
//		loginEmailCookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간을 24시간으로 설정
//		loginEmailCookie.setHttpOnly(true); // JavaScript를 통한 쿠키 접근 방지
//		loginIdCookie.setPath("/"); // 쿠키의 경로 설정
//		loginIdCookie.setSecure(true); // HTTPS에서만 쿠키를 전송하려면 주석 해제

//		response.addCookie(loginEmailCookie);
		TokenMemberInfoDto tokenMemberInfoDto = memberService.loginCheckMember(memberLoginRequestDto);
		TokenDto tokenDto = jwtService.issueToken(tokenMemberInfoDto);
		MemberLoginResponseDto memberLoginResponseDto = MemberLoginResponseDto.builder()
				.memberInfo(tokenMemberInfoDto)
				.token(tokenDto)
				.build();

		return ResponseEntity.ok().body(Message.success(memberLoginResponseDto));
	}

	@GetMapping("/logout")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> logoutMember(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		memberService.logoutMember(memberLoginActiveDto.getEmail());
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/{memberId}/password")
	public ResponseEntity<Message<String>> getPasswordMember(@PathVariable("memberId") Long memberId) {
		String memberPassword = memberService.getPasswordMember(memberId);
		return ResponseEntity.ok().body(Message.success("비밀번호 찾기: " + memberPassword));
	}

	// 임시 비밀번호 재발급
	@GetMapping("/temp/password")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> sendTempPassword(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		MailCodeDto mailCodeDto = emailService.sendSimpleMessage(memberLoginActiveDto.getEmail(), false);
		// 여기에 임시 비밀번호 재발급 하는 service 부르기


		return ResponseEntity.ok().body(Message.success());
	}

	// Refresh 토큰 Redis에 저장되어 있는지 확인해서 AccessToken 재발급 받는 API (Access 토큰 만료 됐을 시 프론트쪽에서 한번 더 부를 API)
	@GetMapping("/token/redis")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> refreshTokenCheck(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		jwtService.reissueToken(memberLoginActiveDto.getEmail());
		return ResponseEntity.ok().body(Message.success());
	}


}
