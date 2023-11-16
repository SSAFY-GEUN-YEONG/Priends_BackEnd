package com.ssafy.priends.domain.member.dto;

import com.ssafy.priends.domain.member.dto.enums.MemberRole;
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
	private String nickname;
	private String image;
	private boolean status;
	private boolean alarm;
	private MemberRole role;
}
