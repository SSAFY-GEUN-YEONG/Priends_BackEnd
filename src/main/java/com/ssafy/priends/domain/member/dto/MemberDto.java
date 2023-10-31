package com.ssafy.priends.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDto {
	private Long id;
	private String email;
	private String password;
	private boolean status;
	private boolean alerm; 
}
