package com.ssafy.priends.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.priends.domain.member.dto.MemberDto;
import com.ssafy.priends.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {	
	
	private final MemberService memberService;
	
	@PostMapping("/{email}/check")
	public ResponseEntity<?> emailCheckMember(@PathVariable("email") String userEmail) {
		int emailCheck = memberService.emailCheckMember(userEmail);
		return ResponseEntity.ok().body(emailCheck);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpMember(@RequestBody MemberDto memberDto) {
		memberService.signUpMember(memberDto);
		return ResponseEntity.ok().body(memberDto);
	}
}
