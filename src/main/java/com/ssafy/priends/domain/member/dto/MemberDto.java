package com.ssafy.priends.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberDto {
	private Long id;
	private String email;
	private String password;
	private boolean status;
	private boolean alarm;
	private String role;
}
