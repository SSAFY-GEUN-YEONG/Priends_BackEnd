package com.ssafy.priends.domain.member.controller;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.dto.MemberGetDto;
import com.ssafy.priends.domain.member.dto.MemberInfoDto;
import com.ssafy.priends.domain.member.dto.MemberLoginRequestDto;
import com.ssafy.priends.global.common.dto.MailCodeDto;
import com.ssafy.priends.global.common.dto.Message;
import com.ssafy.priends.global.infra.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/{email}/check")
	public ResponseEntity<Message<String>> emailCheckMember(@PathVariable("email") String memberEmail) {
		int emailCheck = memberService.emailCheckMember(memberEmail);
		return ResponseEntity.ok().body(Message.success( "이메일 중복 여부(0=없음, 1= 있음): " + emailCheck));
	}

	@PostMapping("/signup")
	public ResponseEntity<Message<String>> signUpMember(@RequestBody MemberDto memberDto) {
		memberService.signUpMember(memberDto);
		return ResponseEntity.ok().body(Message.success("회원가입 성공"));
	}

	@PutMapping("/update")
	public ResponseEntity<Message<String>> updateMember(@RequestBody MemberDto memberDto) {
		memberService.updateMember(memberDto);
		return ResponseEntity.ok().body(Message.success("회원정보 수정 성공"));
	}

	@GetMapping("/{memberId}/get")
	public ResponseEntity<Message<MemberGetDto>> getMember(@PathVariable("memberId") Long memberId) {
		MemberGetDto memberGetDto = memberService.getMember(memberId);
		return ResponseEntity.ok().body(Message.success(memberGetDto));
	}

	@PutMapping("/{memberId}/delete")
	public ResponseEntity<Message<String>> deleteMember(@PathVariable("memberId") Long memberId) {
		memberService.deleteMember(memberId);
		return ResponseEntity.ok().body(Message.success("회원 탈퇴 성공"));
	}

	@PostMapping("/login")
	public ResponseEntity<Message<MemberInfoDto>> loginMember(@RequestBody MemberLoginRequestDto memberLoginRequestDto, HttpSession httpSession, HttpServletResponse response) {
		MemberInfoDto memberInfoDto = memberService.loginMember(memberLoginRequestDto);
		httpSession.setAttribute("loginMember", memberLoginRequestDto);
		// 로그인 이메일 정보를 쿠키에 저장 (로그인 하기 전 이메일 저장)
		Cookie loginEmailCookie = new Cookie("savedEmail", memberLoginRequestDto.getEmail());
		loginEmailCookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간을 24시간으로 설정
		loginEmailCookie.setHttpOnly(true); // JavaScript를 통한 쿠키 접근 방지
//		loginIdCookie.setPath("/"); // 쿠키의 경로 설정
//		loginIdCookie.setSecure(true); // HTTPS에서만 쿠키를 전송하려면 주석 해제

		response.addCookie(loginEmailCookie);
		return ResponseEntity.ok().body(Message.success(memberInfoDto));
	}

	@GetMapping("/logout")
	public ResponseEntity<Message<String>> logoutMember(HttpSession httpSession) {
//		httpSession.invalidate();
		// 나중에 서비스단에서 logout 호출해서 redis에 저장된 token값 삭제해줘야함
		return ResponseEntity.ok().body(Message.success("로그아웃 성공"));
	}

	@GetMapping("/{memberId}/password")
	public ResponseEntity<Message<String>> getPasswordMember(@PathVariable("memberId") Long memberId) {
		String memberPassword = memberService.getPasswordMember(memberId);
		return ResponseEntity.ok().body(Message.success("비밀번호 찾기: " + memberPassword));
	}

	// 임시 비밀번호 재발급
	@GetMapping("/{memberEmail}/temp/password")
	public ResponseEntity<Message> sendTempPassword(@PathVariable("memberEmail") String memberEmail) {
		// 먼저 db에 사용자 이메일이 저장되어 있는지 확인하기

		MailCodeDto mailCodeDto = emailService.sendSimpleMessage(memberEmail, false);
		// 여기에 임시 비밀번호 재발급 하는 service 부르기
		return ResponseEntity.ok().body(Message.success());
	}

}
