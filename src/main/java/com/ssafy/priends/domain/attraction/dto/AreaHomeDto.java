package com.ssafy.priends.domain.attraction.dto;

import java.util.List;

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
public class AreaHomeDto {
	private String img;	//대표이미지
	private List<AttractionDto> natureAttractions;
	private List<AttractionDto> restaurantAttractions;
	private List<AttractionDto> cultureAttractions;
	
}
