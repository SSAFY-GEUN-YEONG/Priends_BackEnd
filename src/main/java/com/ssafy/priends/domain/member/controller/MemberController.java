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

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
	public ResponseEntity<Message<Void>> updateMember(@RequestBody MemberDto memberDto,
													  @AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		memberDto.setId(memberLoginActiveDto.getId());
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
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> deleteMember(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		memberService.deleteMember(memberLoginActiveDto.getId());
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping("/login")
	public ResponseEntity<Message<MemberLoginResponseDto>> loginMember(@RequestBody MemberLoginRequestDto memberLoginRequestDto,
																	   HttpServletResponse response) {

		TokenMemberInfoDto tokenMemberInfoDto = memberService.loginCheckMember(memberLoginRequestDto);
		TokenDto tokenDto = jwtService.issueToken(tokenMemberInfoDto);
		MemberLoginResponseDto memberLoginResponseDto = MemberLoginResponseDto.builder()
				.memberInfo(tokenMemberInfoDto)
				.token(tokenDto)
				.build();

		// JWT 토큰을 쿠키에 저장
		Cookie accessTokenCookie = new Cookie("accessToken", tokenDto.getAccessToken());
//		accessTokenCookie.setHttpOnly(true);	// 이거 설정하면 클라이언트단에서 cookie 가져오기 불가능 (get 불가)
		accessTokenCookie.setPath("/");
		accessTokenCookie.setMaxAge(3600); // 60분(3600초)으로 설정
//		log.info(accessTokenCookie.getValue());
		response.addCookie(accessTokenCookie);
		// 필요에 따라 Secure 플래그 설정
		// accessTokenCookie.setSecure(true);

		return ResponseEntity.ok().body(Message.success(memberLoginResponseDto));
	}

	@GetMapping("/logout")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> logoutMember(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto,
													  HttpServletResponse response) {
		memberService.logoutMember(memberLoginActiveDto.getEmail());

		// 쿠키 삭제
		Cookie accessTokenCookie = new Cookie("accessToken", null);
		accessTokenCookie.setMaxAge(0);
		accessTokenCookie.setPath("/");
		response.addCookie(accessTokenCookie);
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/{memberId}/password")
	public ResponseEntity<Message<String>> getPasswordMember(@PathVariable("memberId") Long memberId) {
		String memberPassword = memberService.getPasswordMember(memberId);
		return ResponseEntity.ok().body(Message.success("비밀번호 찾기: " + memberPassword));
	}

	// 임시 비밀번호 재발급
	@GetMapping("/{email}/temp/password")
	public ResponseEntity<Message<Void>> sendTempPassword(@PathVariable("email") String memberEmail) {
		MailCodeDto mailCodeDto = emailService.sendSimpleMessage(memberEmail, false);
		memberService.sendTempPassword(memberEmail, mailCodeDto);
		return ResponseEntity.ok().body(Message.success());
	}

	// Refresh 토큰 Redis에 저장되어 있는지 확인해서 AccessToken 재발급 받는 API (Access 토큰 만료 됐을 시 프론트쪽에서 한번 더 부를 API)
	@GetMapping("/token/redis")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> refreshTokenCheck(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		jwtService.reissueToken(memberLoginActiveDto.getEmail());
		return ResponseEntity.ok().body(Message.success());
	}

	// 비밀번호 변경
	@PutMapping("/password/update")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Message<Void>> updatePasswordMember(@RequestBody MemberPasswordUpdateDto memberPasswordUpdateDto,
															  @AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
		memberPasswordUpdateDto.setId(memberLoginActiveDto.getId());
		memberService.updatePasswordMember(memberPasswordUpdateDto);
		return ResponseEntity.ok().body(Message.success());
	}

}
