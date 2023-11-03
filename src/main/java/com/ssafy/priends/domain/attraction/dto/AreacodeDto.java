package com.ssafy.priends.domain.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor	// 필드 전부 생성자
@NoArgsConstructor	// 기본 생성자
public class AreacodeDto {
	private String sido_code;
	private String sido_name;
	private String gugun_code;
	private String gugun_name;
	
	
}
